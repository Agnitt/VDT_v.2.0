package com.agnitt.vdt.library

import android.content.res.ColorStateList
import android.util.Log
import android.widget.RadioButton
import com.agnitt.vdt.*
import com.agnitt.vdt.Utils.Companion.APP
import kotlinx.android.synthetic.main.tmpl_radio_group.view.*

class RadioGroup {
    fun create(id: Int, parent: VG?, text: String, dataSet: List<Float>, checkedValueIndex: Int) =
        ((parent inflate R.layout.tmpl_radio_group) as RG).apply {
            tw_radio_group.text = text
            this.id = id
            dataSet.forEachIndexed { i, value ->
                this.addView(
                    rButton(getUniqueID() + i, value.toString(), i, checkedValueIndex == i)
                )
            }
            setOnCheckedChangeListener { group, id ->
                pressRadioGroup(group, get(id))
            }
        }.apply { parent?.addView(this) }

    private fun rButton(id: Int, value: String, position: Int, isChecked: Boolean = false) =
        RadioButton(APP).apply {
            this.id = id
            text = value
            tag = position
            this.isChecked = isChecked
            if (isChecked) {
                setTextColor(get<Int>(R.color.radioGroupAccent))
                buttonTintList = (ColorStateList(
                    arrayOf(intArrayOf(android.R.attr.state_enabled)),
                    intArrayOf(get(R.color.radioGroupAccent))
                ))
            }
            Log.d("LOG_RB_IDS", id.toString())
        }
}

fun RadioButton.checked() {
    if (!isChecked) {
        setTextColor(get<Int>(R.color.radioGroupAccent))
        buttonTintList = (ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)),
            intArrayOf(get(R.color.radioGroupAccent))
        ))
        isChecked = true
    }
}