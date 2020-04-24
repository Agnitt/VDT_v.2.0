package com.agnitt.vdt.data

import com.agnitt.vdt.models.Page
import com.agnitt.vdt.models.PageItem
import com.agnitt.vdt.utils.GET_ALL_PAGES
import com.agnitt.vdt.utils.LOCAL_HOST
import com.agnitt.vdt.utils.Utils.Companion.APP
import com.agnitt.vdt.utils.log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley


class Parser {
    init {
        parser = this
    }

    companion object {
        lateinit var parser: Parser
    }

    fun init() {
        val requestQueue = Volley.newRequestQueue(APP)
        "REQUEST START".log()

        val request = object : JsonArrayRequest(
            Method.GET, LOCAL_HOST + GET_ALL_PAGES, null,
            Response.Listener {
                "LISTENER".log()
                it.getPages()
            },
            Response.ErrorListener { "ERROR LISTENER".log() }) {
            override fun getHeaders(): Map<String, String> {
                "GET HEADER".log()
                return hashMapOf(Pair("Accept", "application/json"))
            }
        }

        "REQUEST END $request".log()
        requestQueue.add(request)
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

    fun getReaction(id: Int, currentValue: Float) {}
}



