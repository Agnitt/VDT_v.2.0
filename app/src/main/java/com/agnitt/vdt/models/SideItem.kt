package com.agnitt.vdt.models

import androidx.room.*
import com.agnitt.vdt.*
import com.agnitt.vdt.data.DataListConverter

@Entity(
    tableName = TABLE_OF_SIDE_ITEMS
)
class SideItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = KEY_SIDE_ITEM_ID, index = true)
    var sideItemId: Long = 0
    @ColumnInfo(name = KEY_OWNER_ID)
    var ownerId: Long = 0
    @ColumnInfo(name = KEY_NAME)
    var name: String
    @ColumnInfo(name = KEY_MEASURE)
    var measure: String
    @ColumnInfo(name = KEY_TYPE)
    var type: String
    @TypeConverters(DataListConverter::class)
    @ColumnInfo(name = KEY__DATALIST)
    var dataList: List<Float>
    @ColumnInfo(name = KEY_CURRENT_VALUE)
    var currentValue: Float? = null

    constructor(
        sideItemId: Long,
        ownerId: Long,
        name: String,
        measure: String,
        type: String,
        dataList: List<Float>,
        currentValue: Float? = null
    ) {
        this.sideItemId = sideItemId
        this.ownerId = ownerId
        this.name = name
        this.measure = measure
        this.type = type
        this.dataList = dataList
        this.currentValue = currentValue
    }

    @Ignore
    constructor(
        ownerId: Long,
        name: String,
        measure: String,
        type: String,
        dataList: List<Float>,
        currentValue: Float? = null
    ) {
        this.ownerId = ownerId
        this.name = name
        this.measure = measure
        this.type = type
        this.dataList = dataList
        this.currentValue = currentValue
    }

    override fun toString(): String =
        "\n[SIDE]\nsideItemId = $sideItemId\nownerId = $ownerId\nname = $name\nmeasure = $measure\ntype = $type\ndataList = $dataList\ncurrentValue = $currentValue"
}