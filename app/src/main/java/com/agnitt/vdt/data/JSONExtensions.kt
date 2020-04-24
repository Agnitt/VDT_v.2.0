package com.agnitt.vdt.data

import com.agnitt.vdt.builders.PageBuilder.Companion.pages
import com.agnitt.vdt.models.*
import com.agnitt.vdt.utils.addIfNotNull
import com.agnitt.vdt.utils.log
import org.json.JSONArray
import org.json.JSONObject

fun JSONArray.getPages() = try {
    "TRY".log()
    val jsonArr = this
    "ARR: $jsonArr".log()

    for (i in 0 until jsonArr.length()) {
        val jsonObject = jsonArr.getJSONObject(i)
        val type = jsonObject.getParam("type")

        val mainItems = mutableListOf<MainItem>()
        jsonObject.getJSONArray("mainItems").apply {
            for (j in 0 until length()) {
                getJSONObject(i).apply {
                    mainItems.addIfNotNull(
                        when (type) {
                            Types.CHART.name -> Chart(
                                chartId = getParam("id").toLong(),
                                ownerId = getParam("owner").toLong(),
                                name = getParam("name"),
                                measure = getParam("measure"),
                                type = getParam("type"),
                                basicDataList = getList("basicDataList"),
                                modelDataList = getList("modelDataList"),
                                strategyData = getParam("strategyData").toFloat()
                            )
                            Types.TABLE.name -> Table(
                                tableId = getParam("id").toLong(),
                                ownerId = getParam("owner").toLong(),
                                dataList = getList("dataList")

                            )
                            else -> null
                        }
                    )
                }

            }
        }

        val sideItems = mutableListOf<SideItem>()
        jsonObject.getJSONArray("sideItems").apply {
            for (k in 0 until length()) {
                getJSONObject(i).apply {
                    sideItems.add(
                        SideItem(
                            sideItemId = getParam("id").toLong(),
                            ownerId = getParam("owner").toLong(),
                            name = getParam("name"),
                            type = getParam("type"),
                            dataList = getList("dataList"),
                            sectionCount = getParam("sectionCount").toInt(),
                            currentValue = getParam("currentValue").toFloat()
                        )
                    )
                }

            }
        }

        pages.add(
            Page(
                id = jsonObject.getParam("id").toLong(),
                name = jsonObject.getParam("name"),
                type = type,
                mainItems = mainItems,
                sideItems = sideItems
            )
        )
    }
} catch (e: Exception) {
    e.printStackTrace()
}

fun JSONObject.getParam(parameter: String) = this[parameter].toString()
fun JSONObject.getList(listName: String) =
    mutableListOf<Float>().apply {
        var arr = this@getList.getJSONArray(listName)
        for (i in 0 until arr.length())
            addIfNotNull(arr.getString(i).toFloat())
    }