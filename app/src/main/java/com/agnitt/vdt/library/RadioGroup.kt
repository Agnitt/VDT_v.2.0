package com.agnitt.vdt.library

import android.content.res.ColorStateList
import android.widget.RadioButton
import androidx.core.view.forEachIndexed
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.data.Parser.Companion.parser
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
        var checkedIndexesRG = arrayOf(2, 2, 3, 3)
    }

    fun resetCheckedIndexesRG() {
        checkedIndexesRG = arrayOf(2, 2, 3, 3)
    }

    fun create(
        position: Int?, id: Int, parent: VG?, text: String,
        dataSet: List<Float>, checkedIndexStart: Int
    ) = ((parent inflate R.layout.tmpl_radio_group) as RG).apply {
        val checkedSave: Int

        if (preferences.contains(id.toString())) {
            checkedSave = preferences.get<Int>(id.toString())!!
            isSave = true
        } else checkedSave = checkedIndexStart
        parser.getReaction(id, dataSet[checkedSave])

        this.id = id
        tw_radio_group.text = text

        dataSet.forEachIndexed { i, value ->
            this.addView(
                rButton(
                    id * 10000 + i,
                    value,
                    i,
                    checkedSave == i,
                    checkedIndexStart == i
                )
            )
        }
        setOnCheckedChangeListener { group, id ->
            parser.getReaction(group.id, get<RB>(id).text.toString().toFloat())
        }
    }.apply { parent?.add(this, position) }

    private fun rButton(id: Int, value: Float, position: Int, isChecked: Boolean, start: Boolean) =
        RadioButton(APP).apply {
            this.id = id
            text = value.toString()
            tag = position
            this.isChecked = isChecked
            if (start) {
                setTextColor(get<Int>(R.color.radioGroupAccent))
                buttonTintList = (ColorStateList(
                    arrayOf(intArrayOf(android.R.attr.state_enabled)),
                    intArrayOf(get(R.color.radioGroupAccent))
                ))
            }
        }
}

fun RG.getCheckedValue(): String? {
    if (this.checkedRadioButtonId == -1) return null
    return ACT.findViewById<RB>(this.checkedRadioButtonId).text.toString()
}

fun RG.getPositionOfCheckedButton(): Int {
    var position = 0
    forEachIndexed { i, rb -> if (i > 0 && (rb as RB).isChecked) position = i - 1 }
    return position
}