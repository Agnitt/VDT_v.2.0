package com.agnitt.vdt.data

const val LOCAL_HOST = "http://10.0.2.2:8080"
const val GET_ALL_PAGES = "/get/page/all"
val GET_CHANGED_ITEMS = { id: Int, currentValue: Float -> "/change/$id/$currentValue" }