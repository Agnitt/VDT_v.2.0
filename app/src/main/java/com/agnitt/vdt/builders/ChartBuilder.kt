package com.agnitt.vdt.builders

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.ChartsBuilder.Companion.chartsBuilder
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.models.Chart
import com.agnitt.vdt.utils.*
import com.agnitt.vdt.utils.Utils.Companion.YEAR
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.chart_dashboard.*
import kotlinx.android.synthetic.main.chart_dashboard.view.*
import kotlinx.android.synthetic.main.tmpl_chart.view.*
import kotlin.math.absoluteValue

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
        notifyDataSetChanged()
        val dataBasicChart = LineDataSet(basicDataList.toEntries(), "")
        val dataModelChart = LineDataSet(modelDataList.toEntries(), "")
        val dataNextYearStrategy = LineDataSet(listOf(strategyValue).toEntries(1), "")

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

    fun LineChart.setGesture(isBig: Boolean) = this.apply {
        setTouchEnabled(!isBig) // Включает / отключает все возможные сенсорные взаимодействия с графиком.
        isDragEnabled = false // перетаскивание (панорамирование) для графика.
        setScaleEnabled(false) // масштабирование для диаграммы по обеим осям.
        isScaleXEnabled = false // масштабирование по оси x.
        isScaleYEnabled = false // масштабирование по оси Y.
        setPinchZoom(false) // масштабирование по пинчу включено. Если отключено, оси X и Y можно увеличивать отдельно.
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

    @SuppressLint("ClickableViewAccessibility")
    infix fun VG.listen(chart: LineChart) = chart.setOnTouchListener { v, e ->
        if (e.action == MotionEvent.ACTION_DOWN)
            when (v.parent.parent) {
                is FL -> chart.open(this)
                is CL -> chart.hide()
                else -> false
            }
        else false
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
        val chartCL = this.parent as CL
        (chartCL.parent as FL).removeView(chartCL)
        parent.forEach { v -> v.visibility = VG.INVISIBLE }
        chartCL.tv_label_name.textSize *= 1.2f
        chartCL.tv_label_values.textSize *= 1.2f
        parent.addView(chartCL)
        return true
    }

    fun LineChart.hide(): Boolean {
        val position = this.tag.toString().toInt()
        val chartCL = this.parent as CL
        val frame = (chartCL.parent as CL).getFrame(position) ?: return false
        (chartCL.parent as CL).apply {
            removeView(chartCL)
            forEach { v -> v.visibility = VG.VISIBLE }
        }
        chartCL.tv_label_name.textSize /= 1.2f
        chartCL.tv_label_values.textSize /= 1.2f
        frame.addView(chartCL)
        return true
    }

    fun TextView.setLabel(primary: String, secondary: String, isName: Boolean, isBig: Boolean) =
        this.setText(
            setStyleLabel(primary, secondary, isName, isBig),
            TextView.BufferType.SPANNABLE
        )

    fun setStyleLabel(text1: String, text2: String, isName: Boolean, isBig: Boolean) =
        SpannableString(isName so "$text1,   $text2" ?: "$text1   ($text2)").apply {
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

    fun changeLabelValues(year: Int?) = if (year != null) chartsBuilder.lineCharts.forEach {
        val basic = it.data.dataSets[0].getEntryForIndex(year - YEAR).y
        val model = it.data.dataSets[1].getEntryForIndex(year - YEAR).y
        (it.parent as CL).tv_label_values.setLabel(
            String.format("%.1f", basic), String.format("%.2f", (basic - model).absoluteValue),
            false, (it.parent.parent as VG).id == ACT.fl_main_chart.id
        )
    } else null

    @SuppressLint("ClickableViewAccessibility")
    infix fun MutableList<LineChart>.listen(acButton: AppCompatButton) =
        acButton.setOnTouchListener { button, event ->
            if (event.action != MotionEvent.ACTION_DOWN) return@setOnTouchListener false
            button.isPressed = !button.isPressed
            this.forEach { it.showValues() }
            return@setOnTouchListener true
        }

    fun LineChart.showValues() = this.apply {
        notifyDataSetChanged()
        data = data.apply {
            dataSets.let {
                if (get<AppCompatButton>(R.id.b_basic_v).isPressed) it[0].changeDisplayMode()
                if (get<AppCompatButton>(R.id.b_model).isPressed) it[1].changeDisplayMode()
                if (get<AppCompatButton>(R.id.b_strategy).isPressed) it[2].changeDisplayMode()
            }
        }
        invalidate()
    }

    fun ILineDataSet.changeDisplayMode() = this.setDrawValues(!this.isDrawValuesEnabled)

    fun setRGYearsParams() = ACT.findViewById<RG>(R.id.rg_years).apply {
        forEachIndexed { i, child -> if (child is RadioButton) child.text = (YEAR + i).toString() }
        setOnCheckedChangeListener { _, id ->
            changeLabelValues(get<RB>(id).text.toString().toInt())
        }
    }
}
