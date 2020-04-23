package com.agnitt.vdt.builders

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import com.agnitt.vdt.R
import com.agnitt.vdt.models.Chart
import com.agnitt.vdt.utils.*
import com.agnitt.vdt.utils.Utils.Companion.ACT
import com.agnitt.vdt.utils.Utils.Companion.YEAR
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.chart_dashboard.view.*

interface ChartBuilder {

    fun init(): ChartsBuilder

    fun buildDashboard(charts: List<Chart>): ChartsBuilder

    fun getChart(position: Int): Chart

    fun getLineChart(position: Int): LineChart

    fun parse(chart: Chart): ChartsBuilder

    fun build(position: Int)

    fun rebuild(chart: Chart)

    fun LineChart.setData(
        isBig: Boolean,
        basicDataList: List<Float>, modelDataList: List<Float>, strategyValue: Float,
        showBasicValues: Boolean = false,
        showModelValues: Boolean = false,
        showStrategyValue: Boolean = false
    ): LineChart {
        val dataBasicChart = LineDataSet(basicDataList.toEntries(), "")
        val dataModelChart = LineDataSet(modelDataList.toEntries(), "")
        val dataNextYearStrategy = LineDataSet(listOf(strategyValue).toEntries(), "")

        dataBasicChart.setParamsLine(get(R.color.chartBasic), showBasicValues, isBig)
        dataModelChart.setParamsLine(get(R.color.chartModel), showModelValues, isBig)
        dataNextYearStrategy.setParamsLine(get(R.color.chartStrategy), showStrategyValue, isBig)

        data = LineData(dataBasicChart, dataModelChart, dataNextYearStrategy)
        invalidate()
        return this
    }

    fun LineDataSet.setParamsLine(color: Int, valuesEnable: Boolean, isBig: Boolean) = this.apply {
        mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        cubicIntensity = 0.4f
        setColor(color)
        setCircleColor(color)
        setDrawCircleHole(false)
        circleRadius = 4.5f
        valueTextColor = color
        setDrawValues(valuesEnable)
        valueTextSize /= if (isBig) 1.1f else 1.5f
    }

    fun LineChart.setGesture() = this.apply {
        setTouchEnabled(true) // Включает / отключает все возможные сенсорные взаимодействия с графиком.
        isDragEnabled = false // перетаскивание (панорамирование) для графика.
        setScaleEnabled(false) // масштабирование для диаграммы по обеим осям.
        isScaleXEnabled = false // масштабирование по оси x.
        isScaleYEnabled = false // масштабирование по оси Y.
        setPinchZoom(true) // масштабирование по пинчу включено. Если отключено, оси X и Y можно увеличивать отдельно.
        isDoubleTapToZoomEnabled = false // масштабирование диаграммы двойным нажатием на нее
    }

    fun LineChart.setVisual() = this.apply {
        setDrawGridBackground(false)
        setDrawBorders(false)
        description.isEnabled = false
        legend.isEnabled = false
        xAxis.apply {
            setDrawGridLines(false)
            setDrawLabels(true)
            labelCount = 5
            axisLineWidth = 1F
            textColor = get(R.color.chartAxis)
            axisLineColor = get(R.color.chartAxis)
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            axisMinimum = ((YEAR - 0.3).toFloat())
            axisMaximum = ((YEAR + 4.3).toFloat())
            this.valueFormatter = LabelFormatter()
        }
        axisLeft.apply {
            setDrawAxisLine(false)
            setDrawGridLines(false)
            setDrawLabels(false)
        }
        axisRight.apply {
            setDrawAxisLine(false)
            setDrawGridLines(false)
            setDrawLabels(false)
        }
    }

    fun LineChart.listen(chartsParent: VG) = this.setOnClickListener { v ->
        if (v.parent.parent is FrameLayout) open(chartsParent) else hide()
    }

    fun VG.getFrame(position: Int): FrameLayout? = when (position) {
        0 -> this.fl_main_chart
        1 -> this.fl_small_chart_1
        2 -> this.fl_small_chart_2
        3 -> this.fl_small_chart_3
        4 -> this.fl_small_chart_4
        5 -> this.fl_small_chart_5
        6 -> this.fl_small_chart_6
        else -> null
    }

    fun LineChart.open(parent: VG): Boolean {
        (this.parent.parent as FL).removeView(this.parent as CL)
        parent.forEach { v -> v.visibility = VG.INVISIBLE }
        parent.addView(this.parent as CL)
        return true
    }

    fun LineChart.hide(): Boolean {
        val position = this.tag.toString().toInt()
        val frame = getFrame(position) ?: return false
        (parent.parent as CL).apply {
            removeView(this.parent as CL)
            forEach { v -> v.visibility = VG.VISIBLE }
        }
        frame.addView(this.parent as CL)
        return true
    }

    fun TextView.setLabel(primary: String, secondary: String, inName: Boolean, isBig: Boolean) =
        this.setText(setStyleLabel(primary, secondary, true, isBig), TextView.BufferType.SPANNABLE)

    fun setStyleLabel(text1: String, text2: String, isName: Boolean, isBig: Boolean) =
        SpannableString("$text1    $text2").apply {
            val proportion1: Float
            val proportion2: Float
            if (isName) {
                proportion1 = isBig so 1.3f ?: 1f
                proportion2 = isBig so 1f ?: 0.7f
            } else {
                proportion1 = isBig so 1.8f ?: 1.2f
                proportion2 = isBig so 1.2f ?: 0.9f
            }
            val white = ForegroundColorSpan(isName so get<Int>(R.color.textMuted) ?: Color.WHITE)
            val gray = ForegroundColorSpan(get<Int>(R.color.textMiddle))
            val flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            setSpan(white, 0, text1.length, flag)
            setSpan(gray, text1.length, length, flag)
            setSpan(RelativeSizeSpan(proportion1), 0, text1.length, flag)
            setSpan(RelativeSizeSpan(proportion2), text1.length, length, flag)
        }

    fun LineChart.showValues(onBasic: Boolean, onModel: Boolean, onStrategy: Boolean) =
        this.data.dataSets.let {
            if (onBasic) it[0].setDrawValues(true)
            if (onModel) it[1].setDrawValues(true)
            if (onStrategy) it[2].setDrawValues(true)
        }

    fun LineChart.hideValues(onBasic: Boolean, onModel: Boolean, onStrategy: Boolean) =
        this.data.dataSets.let {
            if (onBasic) it[0].setDrawValues(false)
            if (onModel) it[1].setDrawValues(false)
            if (onStrategy) it[2].setDrawValues(false)
        }

    fun setRadioGroupYearsValues() = ACT.findViewById<RG>(R.id.rg_years)
        .forEachIndexed { i, child -> if (child is RadioButton) child.text = (YEAR + i).toString() }
}
