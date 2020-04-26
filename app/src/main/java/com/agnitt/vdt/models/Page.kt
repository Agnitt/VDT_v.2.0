package com.agnitt.vdt.models

class Page {
    var id: Long = 0
    var name: String
    var type: Types
    var mainItems: List<MainItem>
    var sideItems: List<SideItem>

    constructor(
        id: Long,
        name: String,
        type: Types,
        mainItems: List<MainItem> = listOf(),
        sideItems: List<SideItem> = listOf()
    ) {
        this.id = id
        this.name = name
        this.type = type
        this.mainItems = mainItems
        this.sideItems = sideItems
    }

    constructor(
        name: String,
        type: Types,
        mainItems: List<MainItem> = listOf(),
        sideItems: List<SideItem> = listOf()
    ) {
        this.name = name
        this.type = type
        this.mainItems = mainItems
        this.sideItems = sideItems
    }

    override fun toString(): String =
        "\n[PAGE_MOD]\nid = $id\nname = $name\ntype = ${type.name}\nmainItems = $mainItems\nsideItems = $sideItems"
}

/**
 * class for room
 *

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
"\n\[PAGE_MOD]\nid = $id\nname = $name\ntype = $type\nmainItemsIds = $mainItemsIds\nsideItemsIds = $sideItemsIds"
}
 **/