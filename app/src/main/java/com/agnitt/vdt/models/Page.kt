package com.agnitt.vdt.models

import androidx.room.*
import com.agnitt.vdt.*
import com.agnitt.vdt.data.IdsListConverter

@Entity(tableName = TABLE_OF_PAGES)
class Page {
    @PrimaryKey
    @ColumnInfo(name = KEY_ID, index = true)
    var id: Long = 0

    @ColumnInfo(name = KEY_NAME, index = true)
    var name: String

    @ColumnInfo(name = KEY_TYPE)
    var type: String

    @TypeConverters(IdsListConverter::class)
    @ColumnInfo(name = KEY_MAIN_ITEMS_IDS)
    var mainItemsIds: List<Long>

    @TypeConverters(IdsListConverter::class)
    @ColumnInfo(name = KEY_SIDE_ITEMS_IDS)
    var sideItemsIds: List<Long>

    constructor(
        id: Long,
        name: String,
        type: String,
        mainItemsIds: List<Long> = listOf(),
        sideItemsIds: List<Long> = listOf()
    ) {
        this.id = id
        this.name = name
        this.type = type
        this.mainItemsIds = mainItemsIds
        this.sideItemsIds = sideItemsIds
    }

    @Ignore
    constructor(
        name: String,
        type: String,
        mainItemsIds: List<Long> = listOf(),
        sideItemsIds: List<Long> = listOf()
    ) {
        this.name = name
        this.type = type
        this.mainItemsIds = mainItemsIds
        this.sideItemsIds = sideItemsIds
    }

    override fun toString(): String =
        "\n[PAGE_MOD]\nid = $id\nname = $name\ntype = $type\nmainItemsIds = $mainItemsIds\nsideItemsIds = $sideItemsIds"
}