package com.agnitt.vdt.models

class Table : MainItem {
    var tableId: Long = 0
    override var ownerId: Long = 0
    var dataList: List<Float>

    constructor(tableId: Long, ownerId: Long, dataList: List<Float>) {
        this.tableId = tableId
        this.ownerId = ownerId
        this.dataList = dataList
    }

    constructor(ownerId: Long, dataList: List<Float>) {
        this.ownerId = ownerId
        this.dataList = dataList
    }

    override fun toString(): String =
        "\n[TABLE]\ntableId = $tableId\nownerId = $ownerId\ndataList = $dataList"
}

/**
 * class for room
 *

@Entity(
tableName = TABLE_OF_TABLES
)
class Table {
@PrimaryKey(autoGenerate = true)
@ColumnInfo(name = KEY_TABLE_ID, index = true)
var tableId: Long = 0
@ColumnInfo(name = KEY_OWNER_ID)
var ownerId: Long = 0
@TypeConverters(DataListConverter::class)
@ColumnInfo(name = KEY__DATALIST)
var dataList: List<Float>

constructor(tableId: Long, ownerId: Long, dataList: List<Float>) {
this.tableId = tableId
this.ownerId = ownerId
this.dataList = dataList
}

@Ignore
constructor(ownerId: Long, dataList: List<Float>) {
this.tableId = tableId
this.ownerId = ownerId
this.dataList = dataList
}

override fun toString(): String =
"\n\[TABLE]\ntableId = $tableId\nownerId = $ownerId\ndataList = $dataList"
}

 **/