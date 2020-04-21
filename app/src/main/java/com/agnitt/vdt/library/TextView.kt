package com.agnitt.vdt.library

import com.agnitt.vdt.*

class TextView {
    fun create(id: Int, parent: VG?, text: String, textColor: Int) =
        ((parent inflate R.layout.tmpl_text_view) as TV).apply {
            layoutParams = VG_LP(
                VG_LP.MATCH_PARENT,
                VG_LP.WRAP_CONTENT
            )
            this.text = text
            this.id = id
            setTextColor(get<Int>(textColor))
        }.apply { parent?.addView(this) }
}