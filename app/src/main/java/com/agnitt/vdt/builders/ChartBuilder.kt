package com.agnitt.vdt.builders

import com.agnitt.vdt.utils.Utils.Companion.ACT
import kotlinx.android.synthetic.main.activity_main.*

class ChartBuilder() {
    var parent = ACT.cl_content_main
//    var
    fun init() {

    }

    fun oneChartBuild(
        chartId: Long, name: String, measure: String, type: String,
        basicDataList: List<Float>, modelDataList: List<Float>, strategyData: Float
    ) {

    }
}

/**
 ** chartId
 ** ownerId
 ** name
 ** measure
 ** type
 ** basicDataList
 ** modelDataList
 ** strategyData
 **/