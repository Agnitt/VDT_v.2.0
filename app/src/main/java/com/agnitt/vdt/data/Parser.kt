package com.agnitt.vdt.data

import com.agnitt.vdt.utils.GET_ALL_PAGES
import com.agnitt.vdt.utils.LOCAL_HOST
import com.agnitt.vdt.utils.Utils.Companion.APP
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Parser {
    fun init() {
        var requestQueue = Volley.newRequestQueue(APP)

        var request = JsonObjectRequest(
            Request.Method.GET,
            LOCAL_HOST + GET_ALL_PAGES,
            null,
            Response.Listener { response ->
                try {
//                    if (tv_empty_result.visibility == ViewGroup.VISIBLE) {
//                        recyclerView.visibility = ViewGroup.VISIBLE
//                        tv_empty_result.visibility = ViewGroup.INVISIBLE
//                    }

                    var jsonArr = response.getJSONArray("Search")

                    for (i in 0 until jsonArr.length()) {
                        var jsonObject = jsonArr.getJSONObject(i)
                        var get = { parameter: String -> jsonObject[parameter].toString() }
//                        videos.add(
//                            Video(
//                                name = get("Title"), year = get("Year"), type = get("Type"),
//                                imdbID = get("imdbID"), linkPoster = get("Poster")
//                            )
//                        )
                    }
//                    videoAdapter =
//                        VideoAdapter(this@SearchActivity, SearchActivity::class.java, videos)
//                    recyclerView.adapter = videoAdapter
                } catch (e: Exception) {
//                    recyclerView.visibility = ViewGroup.INVISIBLE
//                    tv_empty_result.visibility = ViewGroup.VISIBLE
                }
            },
            Response.ErrorListener {
//                recyclerView.visibility = ViewGroup.INVISIBLE
//                tv_empty_result.visibility = ViewGroup.VISIBLE
            })
        requestQueue.add(request)
    }
}