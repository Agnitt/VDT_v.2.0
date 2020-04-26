package com.agnitt.vdt.library

import com.agnitt.vdt.R
import com.agnitt.vdt.data.Parser.Companion.parser
import com.agnitt.vdt.data.Saver.Companion.isSave
import com.agnitt.vdt.data.Saver.Companion.preferences
import com.agnitt.vdt.data.get
import com.agnitt.vdt.data.permanentSavingState
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
            if (preferences.contains(id.toString())) {
                isChecked = preferences.get<Boolean>(id.toString())!!
                parser.getReaction(id, if (isChecked) 1f else 0f)
                isSave = true
            } else isChecked = state
            setOnClickListener {
                (it as Sw).permanentSavingState()
                parser.getReaction(it.id, if (isChecked) 1f else 0f)
            }
        }.apply { parent?.add(this, position) }
}