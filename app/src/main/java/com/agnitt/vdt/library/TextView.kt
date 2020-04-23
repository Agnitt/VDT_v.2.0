package com.agnitt.vdt.library

import com.agnitt.vdt.*
import com.agnitt.vdt.utils.*

class TextView {
    init {
        tv = this
    }
    companion object{
        lateinit var tv: TextView
    }
    fun create(position:Int?,id: Int, parent: VG?, text: String, textColor: Int) =
        ((parent inflate R.layout.tmpl_text_view) as TV).apply {
            layoutParams = VG_LP(
                VG_LP.MATCH_PARENT,
                VG_LP.WRAP_CONTENT
            )
            this.text = text
            this.id = id
            setTextColor(com.agnitt.vdt.utils.get<Int>(textColor))
        }.apply { parent?.add(this, position) }
}