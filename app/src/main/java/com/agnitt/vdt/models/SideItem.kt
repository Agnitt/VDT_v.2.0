package com.agnitt.vdt.models

class SideItem : PageItem {
    var sideItemId: Long = 0
    var ownerId: Long = 0
    var name: String
    var type: Types
    var dataList: List<Float>
    var sectionCount: Int = 4
    var currentValue: Float? = null

    constructor(
        sideItemId: Long,
        ownerId: Long,
        name: String,
        type: Types,
        dataList: List<Float>,
        sectionCount: Int,
        currentValue: Float? = null
    ) {
        this.sideItemId = sideItemId
        this.ownerId = ownerId
        this.name = name
        this.type = type
        this.dataList = if (type == Types.SWITCH_SLIDER || type == Types.POPUP_SWITCH_SLIDER)
            dataList.map { it * 10 } else dataList
        this.sectionCount = sectionCount
        this.currentValue = if (type == Types.SWITCH_SLIDER || type == Types.POPUP_SWITCH_SLIDER)
            currentValue!! * 10 else currentValue
    }

    constructor(
        ownerId: Long,
        name: String,
        type: Types,
        dataList: List<Float>,
        sectionCount: Int,
        currentValue: Float? = null
    ) {
        this.ownerId = ownerId
        this.name = name
        this.type = type
        this.dataList = if (type == Types.SWITCH_SLIDER || type == Types.POPUP_SWITCH_SLIDER)
            dataList.map { it * 10 } else dataList
        this.sectionCount = sectionCount
        this.currentValue = if (type == Types.SWITCH_SLIDER || type == Types.POPUP_SWITCH_SLIDER)
            currentValue!! * 10 else currentValue
    }

    override fun toString(): String =
        "\n[SIDE]\nsideItemId = $sideItemId\nownerId = $ownerId\nname = $name\ntype = ${type.name}\ndataList = $dataList\ncurrentValue = $currentValue"
}

/**
 * class for room
 *


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
"\n\[SIDE]\nsideItemId = $sideItemId\nownerId = $ownerId\nname = $name\nmeasure = $measure\ntype = $type\ndataList = $dataList\ncurrentValue = $currentValue"
}
 **/