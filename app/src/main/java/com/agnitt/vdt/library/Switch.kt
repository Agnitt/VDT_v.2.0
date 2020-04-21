package com.agnitt.vdt.library

import com.agnitt.vdt.*
import kotlin.collections.get

class Switch {
    fun create(id: Int, parent: VG?, text: String, state: Boolean) =
        ((parent inflate R.layout.tmpl_switch) as Sw).apply {
            this.id = id
            setText(text)
            tag = text
            isChecked = state
        }.apply { parent?.addView(this) }
}