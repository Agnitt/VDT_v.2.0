package com.agnitt.vdt.builders

import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.models.Chart
import com.agnitt.vdt.models.Types
import com.agnitt.vdt.utils.CL
import com.agnitt.vdt.utils.VG
import com.agnitt.vdt.utils.inflate
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.chart_dashboard.view.*
import kotlinx.android.synthetic.main.tmpl_chart.view.*
import kotlin.math.absoluteValue

class ChartsBuilder : ChartBuilder {
    init {
        chartsBuilder = this
    }

    companion object {
        lateinit var chartsBuilder: ChartsBuilder
    }

    lateinit var parent: VG
    lateinit var chartsParent: VG
    lateinit var smallChartsParent: VG

    lateinit var charts: MutableList<Chart>
    lateinit var chartsCL: MutableList<CL>
    lateinit var lineCharts: MutableList<LineChart>

    var chartId: Int = 0
    lateinit var name: String
    lateinit var measure: String
    var isBig: Boolean = false

    lateinit var basicDataList: List<Float>
    lateinit var modelDataList: List<Float>
    var strategyData: Float = 0f

    override fun init(): ChartsBuilder {
        parent = ACT.cl_content_main
        return this
    }

    override fun buildDashboard(charts: List<Chart>): ChartsBuilder {
        this.charts = charts.toMutableList()
        lineCharts = mutableListOf()
        chartsCL = mutableListOf()

        chartsParent = (parent inflate R.layout.chart_dashboard) as CL
        parent.removeAllViews()
        parent.addView(chartsParent)

        smallChartsParent = chartsParent.cl_small_charts

        lineCharts listen chartsParent.b_basic_v
        lineCharts listen chartsParent.b_model
        lineCharts listen chartsParent.b_strategy

        setRGYearsParams()
        charts.forEachIndexed { i, chart -> parse(chart).build(i) }
        return this
    }

    override fun getChart(position: Int): Chart = charts[position]

    override fun getLineChart(position: Int): LineChart = lineCharts[position]

    override fun parse(chart: Chart): ChartsBuilder {
        chartId = chart.chartId.toInt()
        name = chart.name
        measure = chart.measure
        isBig = chart.type == Types.BIG.name
        basicDataList = chart.basicDataList
        modelDataList = chart.modelDataList
        strategyData = chart.strategyData
        return this
    }

    override fun build(position: Int) {
        val thisParent = chartsParent.getFrame(position) ?: return

        val chartCL = (thisParent inflate R.layout.tmpl_chart) as CL
        thisParent.removeAllViews()
        thisParent.addView(chartCL)
        chartsCL.add(chartCL)

        val lineChart = chartCL.lc_chart.apply {
            id = chartId
            tag = position
            setData(isBig, basicDataList, modelDataList, strategyData)
            setGesture(isBig)
            setVisual()
            if (!isBig) smallChartsParent listen this
        }
        lineCharts.add(lineChart)

        chartCL.tv_label_name.setLabel(name, measure, true, isBig)
        chartCL.tv_label_values.setLabel(
            basicDataList[0].toString(),
            (basicDataList[0] - modelDataList[0]).absoluteValue.toString(),
            false, isBig
        )
    }

    override fun rebuild(chart: Chart) {
        var position: Int? = null
        parse(chart)
        charts.forEachIndexed { i, ch -> if (ch.chartId == chart.chartId) position = i }
        if (position == null) return
        chartsParent.getFrame(position!!)?.removeAllViews()
        build(position!!)
    }

//
//        chartsCL.forEach {
//        it.tv_label_values.setLabel(
//            basicDataList.sum().toString(),
//            (basicDataList[year - YEAR] - modelDataList[year - YEAR] ).absoluteValue.toString(),
//            false, isBig
//        )
//    }

}

class LabelFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String = value.toInt().toString()
}

/**
 *  chartId: Long, name: String, measure: String, type: String,
 *  basicDataList: List<Float>, modelDataList: List<Float>, strategyData: Float
 *
 *
 ** chartId
 ** ownerId
 ** name
 ** measure
 ** type
 ** basicDataList
 ** modelDataList
 ** strategyData
 **/