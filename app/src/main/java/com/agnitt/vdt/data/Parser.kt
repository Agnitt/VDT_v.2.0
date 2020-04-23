package com.agnitt.vdt.data

import com.agnitt.vdt.models.*
import com.agnitt.vdt.utils.GET_ALL_PAGES
import com.agnitt.vdt.utils.LOCAL_HOST
import com.agnitt.vdt.utils.Utils.Companion.APP
import com.agnitt.vdt.utils.addIfNotNull
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Parser {
    init {
        parser = this
    }

    companion object {
        lateinit var parser: Parser
        var pages = mutableListOf<Page>()
    }

    fun init() {
        val requestQueue = Volley.newRequestQueue(APP)

        val request = JsonObjectRequest(
            Request.Method.GET,
            LOCAL_HOST + GET_ALL_PAGES,
            null,
            Response.Listener { response ->
                try {
                    val jsonArr = response.getJSONArray("")

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
            },
            Response.ErrorListener { })
        requestQueue.add(request)
    }

    fun JSONObject.getParam(parameter: String) = this[parameter].toString()
    fun JSONObject.getList(listName: String) =
        mutableListOf<Float>().apply {
            var arr = this@getList.getJSONArray(listName)
            for (i in 0 until arr.length())
                addIfNotNull(arr.getString(i).toFloat())
        }

//    fun getRelatedObject(idChanger: Int): MainItem {}

    fun MutableList<Page>.changeObjectById(id: Long, item: PageItem) {
//        var obj: PageItem? = null
//        pages.forEach { page ->
//            if (page.type == Types.CHART.name) page.mainItems.forEachIndexed { i, chart ->
//                if ((chart as Chart).chartId == id) {
//                    chart = item as Chart
//                    return@forEach
//                }
//            } else
//        }
    }
}

