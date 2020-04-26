package com.agnitt.vdt.builders

import android.view.View
import android.widget.RadioGroup
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.builders.TableBuilder.Companion.tableBuilder
import com.agnitt.vdt.data.Saver.Companion.isSave
import com.agnitt.vdt.data.Saver.Companion.permanentSavingOn
import com.agnitt.vdt.library.DiscreteSlider.Companion.ds
import com.agnitt.vdt.library.PopupMenu.Companion.pm
import com.agnitt.vdt.library.RadioGroup.Companion.checkedIndexesRG
import com.agnitt.vdt.library.RadioGroup.Companion.rg
import com.agnitt.vdt.library.Switch.Companion.sw
import com.agnitt.vdt.library.SwitchSlider.Companion.sws
import com.agnitt.vdt.library.TextView.Companion.tv
import com.agnitt.vdt.library.getPositionOfCheckedButton
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
    lateinit var type: Types
    lateinit var dataList: List<Float>
    var sectionCount: Int = 4
    var currentValue: Float? = null

    fun init(): SideMenuBuilder {
        parent = ACT.body_menu
        return this
    }

    fun buildSideMenu(items: List<SideItem>, type: Types = Types.CHART): SideMenuBuilder {
        parent.removeAllViews()
        this.items = items
        if (type == Types.CHART) {
            var i = -1
            while (++i < items.size)
                if (items[i].type != Types.POPUP) parse(items[i]).build(i)
                else i = parse(items[i]).buildPopupMenu(i)
        } else buildTableSideMenu(items)
        return this
    }

    private fun buildTableSideMenu(items: List<SideItem>) {
        itemsOfTable.clear()
        val sideMenu = ((parent inflate R.layout.tmpl_table_side_menu) as LL).apply {
            val frame = arrayOf(ll_frame_1, ll_frame_2, ll_frame_3, ll_frame_4)
            val checked = arrayOf(2, 2, 3, 3)
            items.forEachIndexed { i, item ->
                when (i) {
                    0 -> {
                        this.removeView(tv_table_side_menu_name)
                        tv.create(
                            0, item.sideItemId.toInt(), this, item.name,
                            R.color.sideMenuText, inCenter = true, isHeader = true
                        )
                    }
                    in 1..4 -> tableBuilder listen rg.create(
                        null, item.sideItemId.toInt(), frame[i - 1], item.name,
                        item.dataList, checked[i - 1]
                    ).apply { itemsOfTable.add(i - 1, this) }
                    else -> build(i, item.type, this)
                }
            }
        }
        itemsOfTable.forEachIndexed { i, r -> checkedIndexesRG[i] = r.getPositionOfCheckedButton() }
        parent.addView(sideMenu)
        if (isSave || permanentSavingOn) tableBuilder.apply {
            onPressCell(tableBuilder.row(), tableBuilder.column())
            selectTablesRadioButton(tableBuilder.row(), tableBuilder.column())
        }
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

    fun build(position: Int, type: Types = this.type, parent: VG? = this.parent) = when (type) {
        Types.DISCRETE_SLIDER -> ds.create(
            position, sideItemId, parent, name, dataList, currentValue ?: dataList[0], sectionCount
        )
        Types.SWITCH_SLIDER -> sws.create(
            position, sideItemId, parent, name, dataList, currentValue ?: dataList[0]
        )
        Types.SWITCH -> sw.create(position, sideItemId, parent, name, currentValue != 0f)
        Types.RADIO_GROUP -> rg.create(position, sideItemId, parent, name, dataList, sectionCount)
        Types.TEXT_VIEW -> tv.create(
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
            val type = item.type.name
            val pType = Types.POPUP.name
            if (type.contains(pType) && type != pType) {
                val typeItem = type.substring(pType.length + 1)
                pmList.addIfNotNull(parse(item).build(i, Types.valueOf(typeItem), null))
            } else {
                pm.create(position, sideItemId, name, parent, pmList)
                return i - 1
            }
        }
        pm.create(position, sideItemId, name, parent, pmList)
        return items!!.size
    }

}