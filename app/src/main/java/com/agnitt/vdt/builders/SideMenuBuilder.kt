package com.agnitt.vdt.builders

import android.view.View
import android.widget.RadioGroup
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.builders.TableBuilder.Companion.tableBuilder
import com.agnitt.vdt.library.DiscreteSlider.Companion.ds
import com.agnitt.vdt.library.PopupMenu.Companion.pm
import com.agnitt.vdt.library.RadioGroup.Companion.rg
import com.agnitt.vdt.library.Switch.Companion.sw
import com.agnitt.vdt.library.SwitchSlider.Companion.sws
import com.agnitt.vdt.library.TextView.Companion.tv
import com.agnitt.vdt.models.SideItem
import com.agnitt.vdt.models.Types
import com.agnitt.vdt.utils.*
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
    var itemsOfTable: MutableList<RadioGroup> = mutableListOf()

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

    fun buildSideMenu(items: List<SideItem>, type: String = Types.CHART.name): SideMenuBuilder {
        parent.removeAllViews()
        this.items = items
        if (type == Types.CHART.name) {
            var i = -1
            while (++i < items.size)
                if (items[i].type != Types.POPUP.name) parse(items[i]).build(i)
                else i = parse(items[i]).buildPopupMenu(i)
        } else buildTableSideMenu(items)
        return this
    }

    private fun buildTableSideMenu(items: List<SideItem>) {
        val sideMenu = ((parent inflate R.layout.tmpl_table_side_menu) as LL).apply {
            this.tv_table_side_menu_name.apply {
                text = get(R.string.text_sens_nameMenu)
                textSize *= 1.115f
            }
            rg.run {
                tableBuilder listen create(
                    null, items[1].sideItemId.toInt(), ll_frame_1,
                    get(R.string.text_sens_radioGroup1), listOf(12.0f, 14.0f, 16.0f, 18.0f),
                    2
                ).apply { itemsOfTable.add(this) }

                tableBuilder listen create(
                    null, items[2].sideItemId.toInt(), ll_frame_2,
                    get(R.string.text_sens_radioGroup2), listOf(4.9f, 5.1f, 5.2f),
                    2
                ).apply { itemsOfTable.add(this) }

                tableBuilder listen create(
                    null, items[3].sideItemId.toInt(), ll_frame_3,
                    get(R.string.text_sens_radioGroup3), listOf(31.1f, 32.1f, 33.1f, 34.1f),
                    3
                ).apply { itemsOfTable.add(this) }

                tableBuilder listen create(
                    null, items[4].sideItemId.toInt(), ll_frame_4,
                    get(R.string.text_sens_radioGroup4), listOf(1.0f, 1.1f, 1.2f, 1.3f, 1.4f),
                    3
                ).apply { itemsOfTable.add(this) }
            }

        }
        parent.addView(sideMenu)
        tableBuilder.findAndPressCell()
        tableBuilder.findAndPressRB()
    }

    fun getTableSideMenuItem(position: Int): RG? = itemsOfTable[position]

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
        val sideItemId = sideItemId
        val name = name
        val parent = parent
        for (i in position + 1 until items!!.size) {
            val item = items!![i]
            val type = item.type
            val pType = Types.POPUP.name
            if (type.contains(pType) && type != pType)
                pmList.addIfNotNull(parse(item).build(i, type.substring(pType.length + 1), null))
            else {
                pm.create(position, sideItemId, name, parent, pmList)
                return i - 1
            }
        }
        pm.create(position, sideItemId, name, parent, pmList)
        return items!!.size
    }

}