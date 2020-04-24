package com.agnitt.vdt.library

import android.content.res.ColorStateList
import android.widget.RadioButton
import com.agnitt.vdt.R
import com.agnitt.vdt.data.Saver.Companion.isSave
import com.agnitt.vdt.data.Saver.Companion.preferences
import com.agnitt.vdt.data.get
import com.agnitt.vdt.utils.*
import com.agnitt.vdt.utils.Utils.Companion.APP
import kotlinx.android.synthetic.main.tmpl_radio_group.view.*

class RadioGroup {
    init {
        rg = this

    }

    companion object {
        lateinit var rg: RadioGroup
    }

    var checkedIndexesRG = arrayOf(2, 2, 3, 3)

    fun resetcheckedIndexesRG() {
        checkedIndexesRG = arrayOf(2, 2, 3, 3)
    }

    fun create(
        position: Int?, id: Int, parent: VG?, text: String,
        dataSet: List<Float>, checkedValueIndex: Int
    ) = ((parent inflate R.layout.tmpl_radio_group) as RG).apply {

        val checkedIndex: Int
        if (preferences.contains(id.toString())) {
            checkedIndex = preferences.get<Int>(id.toString())!!
            isSave = true
        } else checkedIndex = checkedValueIndex

        tw_radio_group.text = text
        this.id = id
        dataSet.forEachIndexed { i, value ->
            this.addView(
                rButton(
                    getUniqueID(),
                    value.toString(),
                    i,
                    checkedIndex == i,
                    checkedValueIndex == i
                )
            )
        }
    }.apply { parent?.add(this, position) }

    private fun rButton(id: Int, value: String, position: Int, isChecked: Boolean, start: Boolean) =
        RadioButton(APP).apply {
            this.id = id
            text = value
            tag = position
            this.isChecked = isChecked
            if (start) {
                setTextColor(com.agnitt.vdt.utils.get<Int>(R.color.radioGroupAccent))
                buttonTintList = (ColorStateList(
                    arrayOf(intArrayOf(android.R.attr.state_enabled)),
                    intArrayOf(com.agnitt.vdt.utils.get(R.color.radioGroupAccent))
                ))
            }
        }
}