package com.agnitt.vdt.library

import android.content.res.ColorStateList
import android.util.Log
import android.widget.RadioButton
import com.agnitt.vdt.R
import com.agnitt.vdt.data.pressRadioGroup
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

    fun create(
        position: Int?, id: Int, parent: VG?, text: String,
        dataSet: List<Float>, checkedValueIndex: Int
    ) =
        ((parent inflate R.layout.tmpl_radio_group) as RG).apply {
            tw_radio_group.text = text
            this.id = id
            dataSet.forEachIndexed { i, value ->
                this.addView(
                    rButton(getUniqueID() + i, value.toString(), i, checkedValueIndex == i)
                )
            }
            setOnCheckedChangeListener { group, id ->
                pressRadioGroup(
                    group,
                    com.agnitt.vdt.utils.get(id)
                )
            }
        }.apply { parent?.add(this, position) }

    private fun rButton(id: Int, value: String, position: Int, isChecked: Boolean = false) =
        RadioButton(APP).apply {
            this.id = id
            text = value
            tag = position
            this.isChecked = isChecked
            if (isChecked) {
                setTextColor(com.agnitt.vdt.utils.get<Int>(R.color.radioGroupAccent))
                buttonTintList = (ColorStateList(
                    arrayOf(intArrayOf(android.R.attr.state_enabled)),
                    intArrayOf(com.agnitt.vdt.utils.get(R.color.radioGroupAccent))
                ))
            }
            Log.d("LOG_RB_IDS", id.toString())
        }
}

fun RadioButton.checked() {
    if (!isChecked) {
        setTextColor(com.agnitt.vdt.utils.get<Int>(R.color.radioGroupAccent))
        buttonTintList = (ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)),
            intArrayOf(com.agnitt.vdt.utils.get(R.color.radioGroupAccent))
        ))
        isChecked = true
    }
}

//inline fun RG.forEach(action: (view: View) -> Unit) {
//    for (index in 0 until childCount) {
//        action(getChildAt(index))
//    }
//}
//
//inline fun RG.forEachIndexed(action: (index: Int, view: View) -> Unit) {
//    for (index in 0 until childCount) {
//        action(index, getChildAt(index))
//    }
//}