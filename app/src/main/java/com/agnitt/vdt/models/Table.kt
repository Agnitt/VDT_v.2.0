package com.agnitt.vdt.models

import androidx.room.*
import com.agnitt.vdt.KEY_OWNER_ID
import com.agnitt.vdt.KEY_TABLE_ID
import com.agnitt.vdt.KEY__DATALIST
import com.agnitt.vdt.TABLE_OF_TABLES
import com.agnitt.vdt.data.DataListConverter

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
        "\n[TABLE]\ntableId = $tableId\nownerId = $ownerId\ndataList = $dataList"
}