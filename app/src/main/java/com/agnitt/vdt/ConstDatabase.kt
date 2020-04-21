package com.agnitt.vdt

const val DATABASE_NAME = "DATABASE"
const val DATABASE_VERSION = 1
const val TABLE_OF_PAGES = "VDT_PAGES"
const val TABLE_OF_CHARTS = "VDT_CHARTS"
const val TABLE_OF_TABLES = "VDT_TABLES"
const val TABLE_OF_SIDE_ITEMS = "VDT_SIDE_ITEMS"

const val PAGE_TYPE_CHART = "chart"
const val PAGE_TYPE_TABLE = "table"

const val SIDE_ITEM_TYPE_SEEKBAR = "seekbar"
const val SIDE_ITEM_TYPE_SWITCH = "switch"

const val KEY_ID = "key_id"
const val KEY_NAME = "key_name"
const val KEY_TYPE = "key_type"
const val KEY_MAIN_ITEMS_IDS = "key_main_items_ids"
const val KEY_SIDE_ITEMS_IDS = "key_side_items_ids"


const val KEY_OWNER_ID = "key_ownerId"
const val KEY_MEASURE = "key_measure"

const val KEY_CHART_ID = "key_chartId"
const val KEY_BASIC_DATALIST = "key_basicDataList"
const val KEY_MODEL_DATALIST = "key_modelDataList"
const val KEY_STRATEGY_DATA = "key_strategyData"

const val KEY_TABLE_ID = "key_tableId"
const val KEY__DATALIST = "key_dataList"

const val KEY_SIDE_ITEM_ID = "key_sideItemId"
const val KEY_CURRENT_VALUE = "key_currentValue"

const val GET = "SELECT * FROM "
const val BY_ID = " WHERE $KEY_ID LIKE :id"
const val CHART_BY_ID = " WHERE $KEY_CHART_ID LIKE :id"
const val TABLE_BY_ID = " WHERE $KEY_TABLE_ID LIKE :id"
const val SIDE_ITEM_BY_ID = " WHERE $KEY_SIDE_ITEM_ID LIKE :id"
const val BY_NAME = " WHERE $KEY_NAME LIKE :name"
const val BY_OWNER = " WHERE $KEY_OWNER_ID LIKE :ownerId"
const val BY_NAME_AND_OWNER = " WHERE $KEY_NAME LIKE :name AND $KEY_OWNER_ID LIKE :ownerId"




