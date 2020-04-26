package com.agnitt.vdt.data

import com.agnitt.vdt.builders.PageBuilder.Companion.pageBuilder
import com.agnitt.vdt.utils.Utils.Companion.APP
import com.android.volley.RequestQueue
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

    private lateinit var requestQueue: RequestQueue

    fun init() {
        requestQueue = Volley.newRequestQueue(APP)

        val request = object : JsonArrayRequest(
            Method.GET, LOCAL_HOST + GET_ALL_PAGES, null,
            Response.Listener {
                it.getPages()
                pageBuilder.init()
            },
            Response.ErrorListener {}) {
            override fun getHeaders(): Map<String, String> =
                hashMapOf(Pair("Accept", "application/json"))
        }
        requestQueue.add(request)
    }

    fun getReaction(id: Int, currentValue: Float) {
        val request = object : JsonArrayRequest(
            Method.GET, LOCAL_HOST + GET_CHANGED_ITEMS(id, currentValue), null,
            Response.Listener {
                it.changeRelatedItems()
            },
            Response.ErrorListener {}) {
            override fun getHeaders(): Map<String, String> =
                hashMapOf(Pair("Accept", "application/json"))
        }
        requestQueue.add(request)
    }
}



