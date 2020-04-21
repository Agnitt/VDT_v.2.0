package com.agnitt.vdt.models

import androidx.room.*
import com.agnitt.vdt.database.*

@Entity(
    tableName = TABLE_OF_CHARTS
)
class Chart {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = KEY_CHART_ID, index = true)
    var chartId: Long = 0
    @ColumnInfo(name = KEY_OWNER_ID)
    var ownerId: Long = 0

    @ColumnInfo(name = KEY_NAME)
    var name: String

    @ColumnInfo(name = KEY_MEASURE)
    var measure: String

    @ColumnInfo(name = KEY_TYPE)
    var type: String

    @TypeConverters(DataListConverter::class)
    @ColumnInfo(name = KEY_BASIC_DATALIST)
    var basicDataList: List<Float>

    @TypeConverters(DataListConverter::class)
    @ColumnInfo(name = KEY_MODEL_DATALIST)
    var modelDataList: List<Float>? = null

    @ColumnInfo(name = KEY_STRATEGY_DATA)
    var strategyData: Float? = null

    constructor(
        chartId: Long,
        ownerId: Long,
        name: String,
        measure: String,
        type: String,
        basicDataList: List<Float>,
        modelDataList: List<Float>,
        strategyData: Float? = null
    ) {
        this.chartId = chartId
        this.ownerId = ownerId
        this.name = name
        this.measure = measure
        this.type = type
        this.basicDataList = basicDataList
        this.modelDataList = modelDataList
        this.strategyData = strategyData
    }

    @Ignore
    constructor(
        ownerId: Long,
        name: String,
        measure: String,
        type: String,
        basicDataList: List<Float>,
        modelDataList: List<Float>,
        strategyData: Float? = null
    ) {
        this.ownerId = ownerId
        this.name = name
        this.measure = measure
        this.type = type
        this.basicDataList = basicDataList
        this.modelDataList = modelDataList
        this.strategyData = strategyData
    }

    override fun toString(): String =
        "\n[CHART]\nchartId = $chartId\nownerId = $ownerId\nname = $name\nmeasure = $measure\ntype = $type\nbasicDataList = $basicDataList\nmodelDataList = $modelDataList\nstrategyData = $strategyData"
}