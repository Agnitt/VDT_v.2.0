package com.agnitt.vdt.builders

import android.app.Activity
import android.view.MotionEvent
import androidx.core.view.forEach
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.ChartsBuilder.Companion.chartsBuilder
import com.agnitt.vdt.builders.SideMenuBuilder.Companion.sideMenuBuilder
import com.agnitt.vdt.builders.TableBuilder.Companion.tableBuilder
import com.agnitt.vdt.data.Listener
import com.agnitt.vdt.data.Saver.Companion.isSave
import com.agnitt.vdt.data.removeState
import com.agnitt.vdt.data.saveState
import com.agnitt.vdt.library.*
import com.agnitt.vdt.models.Chart
import com.agnitt.vdt.models.Page
import com.agnitt.vdt.models.Table
import com.agnitt.vdt.models.Types
import com.agnitt.vdt.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.side_menu.*
import java.util.*

class PageBuilder(activity: Activity) {
    init {
        ACT = activity

        Listener()

        ChartsBuilder().init()
        TableBuilder().init()
        SideMenuBuilder().init()

        IconButton()
        PopupMenu()
        RadioGroup()
        DiscreteSlider()
        SwitchSlider()
        Switch()
        TextView()

        pageBuilder = this
    }

    companion object {
        lateinit var pageBuilder: PageBuilder
        lateinit var ACT: Activity
        var openPage: String = ""
        val pages = mutableListOf<Page>()
    }

    fun init() {
        pages.forEachIndexed { i, obj ->
            ACT.ll_pages.addView(((ACT.ll_pages inflate R.layout.tmpl_pages) as TV).apply {
                id = getUniqueID()
                text = obj.name
                isPressed = i == 0
                if (i == 0) this.layoutParams = LL_LP(0, LL_LP.MATCH_PARENT, 1f).margins(0, 0, 3, 0)
                setOnTouchListener { page, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        (page.parent as LL).forEach { it.isPressed = it.id == page.id }
                        openPage = text.toString()
                        isSave = false
                        build(text as String)
                        return@setOnTouchListener true
                    }
                    return@setOnTouchListener false
                }
            })
        }

        openPage = pages[0].name
        build(openPage)

        ACT.button_save.setOnClickListener { saveState() }
        ACT.button_reset.setOnClickListener { removeState() }
    }

    fun build(pageName: String): Boolean {
        var page: Page? = null
        pages.forEach {
            if (it.name.toLowerCase(Locale.ROOT) == pageName.toLowerCase(Locale.ROOT)) page = it
        }
        if (page != null) page!!.apply {
            when (type) {
                Types.CHART.name -> {
                    chartsBuilder.buildDashboard(mainItems.map { it as Chart })
                    sideMenuBuilder.buildSideMenu(sideItems)
                }
                Types.TABLE.name -> {
                    tableBuilder.buildDashboard(mainItems.map { it as Table })
                    sideMenuBuilder.buildSideMenu(sideItems, type)
                }
                else -> return false
            }
            return true
        }
        return false
    }

    fun getPageByName(name: String) = pages.run {
        forEach { if (it.name == name) return@run it }
        return@run null
    }
}