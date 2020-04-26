package com.agnitt.vdt.library

import android.view.View
import com.agnitt.vdt.R
import com.agnitt.vdt.utils.*

class TextView {
    init {
        tv = this
    }

    companion object {
        lateinit var tv: TextView
    }

    fun create(
        position: Int?,
        id: Int,
        parent: VG?,
        text: String,
        colorRes: Int,
        inCenter: Boolean = false,
        isHeader: Boolean = false
    ) =
        ((parent inflate R.layout.tmpl_text_view) as TV).apply {
            this.id = id
            layoutParams = VG_LP(
                VG_LP.MATCH_PARENT,
                VG_LP.WRAP_CONTENT
            )
            this.text = text
            if (inCenter) textAlignment = View.TEXT_ALIGNMENT_CENTER
            if (isHeader) textSize *= 1.2f
            setTextColor(get<Int>(colorRes))
        }.apply { parent?.add(this, position) }
}