package com.agnitt.vdt.library

import com.agnitt.vdt.R
import com.agnitt.vdt.utils.Sw
import com.agnitt.vdt.utils.VG
import com.agnitt.vdt.utils.add
import com.agnitt.vdt.utils.inflate

class Switch {
    init {
        sw = this
    }

    companion object {
        lateinit var sw: Switch
    }

    fun create(position: Int?, id: Int, parent: VG?, text: String, state: Boolean) =
        ((parent inflate R.layout.tmpl_switch) as Sw).apply {
            this.id = id
            setText(text)
            tag = text
            isChecked = state
        }.apply { parent?.add(this, position) }
}