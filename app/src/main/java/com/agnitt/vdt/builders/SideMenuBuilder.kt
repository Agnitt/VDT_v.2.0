package com.agnitt.vdt.builders

import android.view.View
import com.agnitt.vdt.R
import com.agnitt.vdt.library.DiscreteSlider.Companion.ds
import com.agnitt.vdt.library.PopupMenu.Companion.pm
import com.agnitt.vdt.library.RadioGroup.Companion.rg
import com.agnitt.vdt.library.Switch.Companion.sw
import com.agnitt.vdt.library.SwitchSlider.Companion.sws
import com.agnitt.vdt.library.TextView.Companion.tv
import com.agnitt.vdt.models.SideItem
import com.agnitt.vdt.models.Types
import com.agnitt.vdt.utils.*
import com.agnitt.vdt.utils.Utils.Companion.ACT
import kotlinx.android.synthetic.main.side_menu.*
import kotlinx.android.synthetic.main.tmpl_table_side_menu.view.*

class SideMenuBuilder {
    init {
        sideMenuBuilder = this
    }

    companion object {
        lateinit var sideMenuBuilder: SideMenuBuilder
    }

    lateinit var parent: VG
    var items: List<SideItem>? = null

    var sideItemId: Int = 0
    lateinit var name: String
    lateinit var type: String
    lateinit var dataList: List<Float>
    var sectionCount: Int = 4
    var currentValue: Float? = null

    fun init(): SideMenuBuilder {
        parent = ACT.body_menu
        return this
    }

    fun buildSideMenu(items: List<SideItem>? = null): SideMenuBuilder {
        if (items != null) {
            this.items = items
            var i = -1
            while (++i < items.size)
                if (items[i].type != Types.POPUP.name) parse(items[i]).build(i)
                else i = parse(items[i]).buildPopupMenu(i)
        } else buildTableSideMenu()
        return this
    }

    private fun buildTableSideMenu() {
        val sideMenu = (parent inflate R.layout.tmpl_table_side_menu).apply {
            tv_table_side_menu_name.apply {
                text = get(R.string.text_sens_nameMenu)
                textSize *= 1.115f
                setTextColor(get<Int>(R.color.radioGroupText))
            }

            rg.run {
                create(
                    null, getUniqueID(), ll_frame_1, get(R.string.text_sens_radioGroup1),
                    listOf(12.0f, 14.0f, 16.0f, 18.0f), 2
                )
                create(
                    null, getUniqueID(), ll_frame_2, get(R.string.text_sens_radioGroup2),
                    listOf(4.9f, 5.1f, 5.2f), 2
                )

                create(
                    null, getUniqueID(), ll_frame_3, get(R.string.text_sens_radioGroup3),
                    listOf(31.1f, 32.1f, 33.1f, 34.1f), 3
                )

                create(
                    null, getUniqueID(), ll_frame_4, get(R.string.text_sens_radioGroup4),
                    listOf(1.0f, 1.1f, 1.2f, 1.3f, 1.4f), 3
                )
            }
        }
        parent.removeAllViews()
        parent.addView(sideMenu)
    }

    fun getSideItem(position: Int): SideItem? = items?.get(position)

    fun parse(item: SideItem): SideMenuBuilder {
        sideItemId = item.sideItemId.toInt()
        name = item.name
        type = item.type
        dataList = item.dataList
        sectionCount = item.sectionCount
        currentValue = item.currentValue
        return this
    }

    fun build(position: Int, type: String = this.type, parent: VG? = this.parent): View? =
        when (type) {
            Types.DISCRETE_SLIDER.name -> ds.create(
                position, sideItemId, parent, name, dataList, currentValue ?: dataList[0],
                sectionCount
            )
            Types.SWITCH_SLIDER.name -> sws.create(
                position, sideItemId, parent, name, dataList, currentValue ?: dataList[0]
            )
            Types.SWITCH.name -> sw.create(position, sideItemId, parent, name, currentValue != 0f)
            Types.RADIO_GROUP.name -> rg.create(
                position, sideItemId, parent, name, dataList, sectionCount
            )
            Types.TEXT_VIEW.name -> tv.create(
                position, sideItemId, parent, name, R.color.sideMenuText
            )
            else -> null
        }

    private fun buildPopupMenu(position: Int): Int {
        val pmList = mutableListOf<View>()
        for (i in position + 1 until items!!.size)
            if (items!![i].type.contains(Types.POPUP.name) && items!![i].type != Types.POPUP.name)
                pmList.addIfNotNull(build(i, type.substring(Types.POPUP.name.length), null))
            else {
                pm.create(position, sideItemId, name, parent, pmList)
                return i - 1
            }
        pm.create(position, sideItemId, name, parent, pmList)
        return items!!.size
    }
}