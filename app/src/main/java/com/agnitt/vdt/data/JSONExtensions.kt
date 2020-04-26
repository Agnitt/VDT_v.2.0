package com.agnitt.vdt.data

import android.util.Log
import com.agnitt.vdt.builders.ChartsBuilder.Companion.chartsBuilder
import com.agnitt.vdt.builders.PageBuilder.Companion.pages
import com.agnitt.vdt.models.*
import com.agnitt.vdt.utils.addIfNotNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun JSONArray.getPages() = try {
    val jsonArr = this

    for (i in 0 until jsonArr.length()) {
        val jsonObject = jsonArr.getJSONObject(i)
        val type = jsonObject.get<Types, Any>("type")
        val id = jsonObject.get<Long, Any>("id")
        val name = jsonObject.get<String, Any>("name")
        val mainItems = when (type) {
            Types.CHART -> jsonObject.getJSONArray("mainItems").getMainObjects<Chart>()
            Types.TABLE -> jsonObject.getJSONArray("mainItems").getMainObjects<Table>()
            else -> mutableListOf()
        }
        val sideItems = jsonObject.getJSONArray("sideItems").getSideMenuObjects()
        pages.add(Page(id, name, type, mainItems, sideItems))
    }
} catch (e: Exception) {
    e.printStackTrace()
}

inline fun <reified T> JSONArray.getMainObjects() = mutableListOf<T>().apply {
    forEach<JSONObject> {
        this@apply.addIfNotNull(
            when (T::class) {
                Chart::class -> Chart(
                    chartId = it.get<Long, Any>("id"),
                    ownerId = it.get<Long, Any>("owner"),
                    name = it.get<String, Any>("name"),
                    measure = it.get<String, Any>("measure"),
                    type = it.get<Types, Any>("type"),
                    basicDataList = it.get<List<Float>, Float>("basicDataList"),
                    modelDataList = it.get<List<Float>, Float>("modelDataList"),
                    strategyData = it.get<Float, Any>("strategyData")
                ) as T
                Table::class -> Table(
                    tableId = it.get<Long, Any>("id"),
                    ownerId = it.get<Long, Any>("owner"),
                    dataList = it.get<List<Float>, Float>("dataList")
                ) as T
                else -> null as T
            }
        )
    }
}

fun JSONArray.getSideMenuObjects() = mutableListOf<SideItem>().apply {
    for (i in 0 until length()) {
        getJSONObject(i).run {
            this@apply.add(
                SideItem(
                    sideItemId = get<Long, Any>("id"),
                    ownerId = get<Long, Any>("owner"),
                    name = get<String, Any>("name"),
                    type = get<Types, Any>("type"),
                    dataList = get<List<Float>, Float>("dataList"),
                    sectionCount = get<Int, Any>("sectionCount"),
                    currentValue = get<Float, Any>("currentValue")
                )
            )
        }
    }
}

fun JSONArray.changeRelatedItems() {
    Log.d("LOG", this.toString())
    if (this.toString().contains("ERROR"))
        return Exception(this.toString()).printStackTrace()
    try {
        getJSONObject(0).getString("type")
        getMainObjects<Chart>().forEach { chartsBuilder.changeDataChart(it) }
    } catch (e: JSONException) {
        getMainObjects<Table>().forEach { }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun <reified T> JSONArray.forEachIndexed(action: (Int, T) -> Unit) {
    for (i in 0 until length()) action(i, get<T>(i))
}

inline fun <reified T> JSONArray.forEach(action: (T) -> Unit) {
    for (i in 0 until length()) action(get<T>(i))
}

inline fun <reified T> JSONArray.get(index: Int): T = when (T::class) {
    JSONArray::class -> getJSONArray(index) as T
    JSONObject::class -> getJSONObject(index) as T
    String::class -> getString(index) as T
    Int::class -> getInt(index) as T
    Long::class -> getLong(index) as T
    Float::class -> getDouble(index).toFloat() as T
    Boolean::class -> getBoolean(index) as T
    else -> get(index) as T
}

inline fun <reified T, reified E> JSONObject.get(parameter: String): T = when (T::class) {
    JSONArray::class -> getJSONArray(parameter) as T
    JSONObject::class -> getJSONObject(parameter) as T
    String::class -> getString(parameter) as T
    Int::class -> getInt(parameter) as T
    Long::class -> getLong(parameter) as T
    Float::class -> getDouble(parameter).toFloat() as T
    Boolean::class -> getBoolean(parameter) as T
    Types::class -> Types.valueOf(getString(parameter)) as T
    List::class -> mutableListOf<E>().apply {
        getJSONArray(parameter).forEach<E> { this.addIfNotNull(it) }
    } as T
    else -> get(parameter) as T
}