package com.agnitt.vdt.builders

import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.text.style.MetricAffectingSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.*
import androidx.core.view.forEachIndexed
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.data.permanentSavingState
import com.agnitt.vdt.library.RadioGroup.Companion.checkedIndexesRG
import com.agnitt.vdt.models.Table
import com.agnitt.vdt.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tmpl_table_side_menu.*

class TableBuilder {
    init {
        tableBuilder = this
    }

    companion object {
        lateinit var tableBuilder: TableBuilder
    }

    private var dWidth: Int = 0
    private var dHeight: Int = 0

    private lateinit var dataList: List<String>
    private lateinit var tableLayout: TableLayout
    private lateinit var parent: VG

    private val rows = 22
    private val columns = 15

    val column = { checkedIndexesRG[1] * 4 + checkedIndexesRG[0] + 3 }
    val row = { checkedIndexesRG[3] * 5 + checkedIndexesRG[2] + 2 }

    private var valNames = arrayListOf("", "", " NIM ", " CIR ", " COR ", "↕ЧКД")
    private var valNimChkd = arrayListOf(
        "", "4.9%", "", "", "", "5.1%", "", "", "", "5.2%", "", "",
        "12.0%", "14.0%", "16.0%", "18.0%",
        "12.0%", "14.0%", "16.0%", "18.0%",
        "12.0%", "14.0%", "16.0%", "18.0%"
    )
    private var valCirCor = arrayListOf(
        "", "1.0%", "", "", "1.1%", "", " 31.1% ", "1.2%", "", "", "1.3%", "", "", "1.4%", "",
        "", "1.0%", "", "", "1.1%", "", " 32.1% ", "1.2%", "", "", "1.3%", "", "", "1.4%", "",
        "", "1.0%", "", "", "1.1%", "", " 33.1% ", "1.2%", "", "", "1.3%", "", "", "1.4%", "",
        "", "1.0%", "", "", "1.1%", "", " 34.1% ", "1.2%", "", "", "1.3%", "", "", "1.4%", ""
    )

    fun init(): TableBuilder {
        parent = ACT.cl_content_main
        dWidth = (ACT.windowManager.defaultDisplay.width * 0.8).toInt()
        dHeight = (ACT.windowManager.defaultDisplay.height * 0.92).toInt()
        return this
    }

    fun buildDashboard(tables: List<Table>): TableBuilder {
        tableLayout = (parent inflate R.layout.table_dashboard) as TableLayout
        parent.removeAllViews()
        parent.addView(tableLayout)

        (tableLayout.layoutParams as CL_LP).apply {
            margins(0, 0, 20, 20)
            leftToRight = CL_LP.PARENT_ID
            topToTop = CL_LP.PARENT_ID
            bottomToBottom = CL_LP.PARENT_ID
            tableLayout.requestLayout()
        }
        tables.forEach { table -> parse(table).build() }
        return this
    }


    fun parse(table: Table): TableBuilder {
        dataList = table.dataList.map { it.toInt().toString() }
        tableLayout.id = table.tableId.toInt()
        return this
    }

    fun build() {
        val funIf = { i: Int, j: Int -> if (i == 0) (j - 3) else (i * 12 + (j - 3)) }
        for (i in 0 until rows) tableLayout.addView(TableRow(ACT.applicationContext).let { tr ->
            for (j in 0 until columns) {
                val tv = ((null as VG?) inflate R.layout.tmpl_cell) as TV
                when {
                    i < 2 && j < 3 -> tr.addView(setElem(tv, i, j, valNames[i * 3 + j]), j)
                    i < 2 && j >= 3 -> tr.addView(setElem(tv, i, j, valNimChkd[funIf(i, j)]), j)
                    j < 3 -> tr.addView(setElem(tv, i, j, valCirCor[(i - 2) * 3 + j]), j)
                    else -> tr.addView(setElem(tv, i, j, this.dataList[(i - 2) * 12 + j - 3]), j)
                }
            }
            return@let tr
        }, i)
    }

    fun rebuild(table: Table) {
        parse(table)
        tableLayout.removeAllViews()
        build()
    }

    private fun setElem(tw: TextView, r: Int, c: Int, elemData: String): View = tw.apply {
        val textColor = when {
            (r < 2 || c < 3) -> get<Int>(R.color.tableTextAccent)
            (r == rows - 2 && c == columns - 2) -> get<Int>(R.color.tableTextStrategy)
            else -> get<Int>(R.color.tableTextPrimary)
        }
        val backColor = get<Int>(R.color.backAccent)

        text = this@TableBuilder.setStyleCell(
            elemData, r == rows - 2 && c == columns - 2, textColor, backColor, r > 1 && c > 2
        )
        background = get<Drawable>(
            when {
                (r == 1 || r == 6 || r == 11 || r == 16) && (c == 2 || c == 6 || c == 10) ->
                    R.drawable.tw_righ_bot_border
                (r == 1 || r == 6 || r == 11 || r == 16) -> R.drawable.tw_bot_border
                (c == 2 || c == 6 || c == 10) -> R.drawable.tw_right_border
                else -> R.color.backAccent
            }
        )
        width = dWidth / if (c < 3) 19 else 17
        height = dHeight / if (r < 2) 24 else 26
        if (c >= 3 && r >= 2) this.setOnClickListener { pressTableElem(it) }
        tag = "[$r][$c]"
    }

    private fun pressTableElem(view: View): Boolean {
        val row: Int = Regex("\\d+").findAll(view.tag.toString()).first().value.toInt()
        val column: Int = Regex("\\d+").findAll(view.tag.toString()).last().value.toInt()

        selectTablesRadioButton(row, column)
        onPressCell(row, column)
        return true
    }

    fun onPressCell(row: Int, column: Int) = if (row > 1 && column > 2) {
        restoreCells()
        for (r in 2..row) setNeighborhoodPressedCell(row, column, r, column)
        for (c in 3 until column) setNeighborhoodPressedCell(row, column, row, c)
    } else null

    fun selectTablesRadioButton(r: Int, c: Int) {
        val getRG = { ll: LinearLayout -> ll.getChildAt(0) as RG }
        val setChecked = { v: View, i: Int, x: Int -> if (v is RadioButton) v.isChecked = i == x }

        val x1 = (c - 3) / 4
        val x2 = c - 3 - 4 * x1
        val y1 = (r - 2) / 5
        val y2 = r - 2 - 5 * y1

        getRG(ACT.ll_frame_1).forEachIndexed { i, view -> setChecked(view, i, x2 + 1) }
        getRG(ACT.ll_frame_2).forEachIndexed { i, view -> setChecked(view, i, x1 + 1) }
        getRG(ACT.ll_frame_3).forEachIndexed { i, view -> setChecked(view, i, y2 + 1) }
        getRG(ACT.ll_frame_4).forEachIndexed { i, view -> setChecked(view, i, y1 + 1) }
    }

    private fun restoreCells() {
        for (r in 2 until rows)
            for (c in 3 until columns)
                resetElem(getView(r, c), r, c)
    }

    private fun setNeighborhoodPressedCell(rowPressed: Int, columnPressed: Int, r: Int, c: Int) =
        getView(r, c).apply {
            val textColor = when {
                r == rows - 2 && c == columns - 2 -> R.color.tableTextStrategy
                r == rowPressed && c == columnPressed -> R.color.tableTextPressedCell
                else -> R.color.tableTextPressedCells
            }
            background = get<Drawable>(R.drawable.tw_back_on_click)
            text = setStyleCell(
                text.substring(1, text.length - 1),
                (r == rows - 2 && c == columns - 2) || (r == rowPressed && c == columnPressed),
                get<Int>(textColor),
                get<Int>(R.color.tableBackPressedCells)
            )
        }

    private fun resetElem(tw: TextView, r: Int, c: Int): View = tw.apply {
        val backColor = get<Int>(R.color.backAccent)
        val textColor = if (r == rows - 2 && c == columns - 2) get<Int>(R.color.tableTextStrategy)
        else get<Int>(R.color.tableTextPrimary)

        text = this@TableBuilder.setStyleCell(
            text.substring(1, text.length - 1), r == rows - 2 && c == columns - 2,
            textColor, backColor
        )

        background = when {
            (r == 1 || r == 6 || r == 11 || r == 16) && (c == 2 || c == 6 || c == 10) ->
                get<Drawable>(R.drawable.tw_righ_bot_border)
            (r == 1 || r == 6 || r == 11 || r == 16) -> get<Drawable>(R.drawable.tw_bot_border)
            (c == 2 || c == 6 || c == 10) -> get<Drawable>(R.drawable.tw_right_border)
            else -> get<Drawable>(R.color.backAccent)
        }
    }

    private fun getView(row: Int, column: Int) = getRow(row).getChildAt(column) as TextView

    private fun getRow(row: Int): TableRow = tableLayout.getChildAt(row) as TableRow

    private fun setStyleCell(
        text: String,
        isBig: Boolean,
        textColor: Int,
        backColor: Int,
        space: Boolean = true
    ): SpannableString {
        val funIf = { a: Any, b: Any -> if (text.contains("↕")) a else b }
        val textSize = if (isBig) 1f else 0.8f
        val dopSize = if (isBig) 0.8f else 1f

        val spanString = SpannableString(funIf(text, "O${text} ") as CharSequence)

        val start = 0
        val middle = if (text != "") 1 else spanString.length
        val end = spanString.length

        val textColorSpan = ForegroundColorSpan(textColor)
        val backColorSpan = ForegroundColorSpan(funIf(textColor, backColor) as Int)

        val relativeSizeSpanNormal = RelativeSizeSpan(textSize)
        val relativeSizeSpanDop = RelativeSizeSpan(dopSize)

        val flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE

        spanString.apply {
            setSpan(textColorSpan, middle, end, flag)
            setSpan(backColorSpan, start, middle, flag)
            setSpan(relativeSizeSpanDop, start, middle, flag)
            setSpan(relativeSizeSpanNormal, middle, end, flag)
            if (!isBig && space) setSpan(CustomSpan(), funIf(start, middle) as Int, end, flag)
        }
        return spanString
    }

    private inner class CustomSpan : MetricAffectingSpan() {
        private var ratio = 0.15

        override fun updateDrawState(paint: TextPaint) {
            paint.baselineShift += (paint.ascent() * ratio).toInt()
        }

        override fun updateMeasureState(paint: TextPaint) {
            paint.baselineShift += (paint.ascent() * ratio).toInt()
        }
    }

    infix fun listen(rg: RG) = rg.apply {
        setOnCheckedChangeListener { group, id ->
            group.permanentSavingState()
            val radio = get<View>(id) as RB
            val values = arrayOf(
                arrayOf(Pair(12.0f, 0), Pair(14.0f, 1), Pair(16.0f, 2), Pair(18.0f, 3)),
                arrayOf(Pair(4.9f, 0), Pair(5.1f, 1), Pair(5.2f, 2)),
                arrayOf(Pair(1.0f, 0), Pair(1.1f, 1), Pair(1.2f, 2), Pair(1.3f, 3), Pair(1.4f, 4)),
                arrayOf(Pair(31.1f, 0), Pair(32.1f, 1), Pair(33.1f, 2), Pair(34.1f, 3))
            )
            for (i in values.indices) for (j in values[i].indices)
                if (radio.text.toString().toFloat() == values[i][j].first)
                    checkedIndexesRG[i] = values[i][j].second
            radio.isChecked = true
            onPressCell(row(), column())
        }
    }
}