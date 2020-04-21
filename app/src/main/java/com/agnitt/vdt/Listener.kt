package com.agnitt.vdt

import android.widget.RadioButton
import android.widget.RadioGroup
import com.agnitt.vdt.library.checked
import com.xw.repo.BubbleSeekBar

class Listener : BubbleSeekBar.OnProgressChangedListener {
    init {
        listener = this
    }

    companion object {
        lateinit var listener: Listener
    }

    override fun onProgressChanged(
        slider: BSB?, progress: Int, progressFloat: Float, fromUser: Boolean
    ) = touchSeekBar(slider!!, progressFloat)


    override fun getProgressOnActionUp(
        slider: BSB?, progress: Int, progressFloat: Float
    ) = touchSeekBar(slider!!, progressFloat)


    override fun getProgressOnFinally(
        slider: BSB?, progress: Int, progressFloat: Float, fromUser: Boolean
    ) = touchSeekBar(slider!!, progressFloat)
}

fun touchSeekBar(slider: BSB, progressFloat: Float) {}

fun pressRadioGroup(group: RadioGroup?, radio: RadioButton) {
    radio.checked()
//    for (i in 0 until group!!.childCount)
//        group.getChildAt(i).apply {
//            if (this == radio) radio.checked() else radio.released()
//        }
}
////    if (group != null && group == ACT.radiogroup_years) {
////        yearUseNow = radio.text.toString().toInt()
////        charts!!.rebuildAll(false)
////    } else {
////        val values = arrayOf(
////            arrayOf(Pair(12.0f, 0), Pair(14.0f, 1), Pair(16.0f, 2), Pair(18.0f, 3)),
////            arrayOf(Pair(4.9f, 0), Pair(5.1f, 1), Pair(5.2f, 2)),
////            arrayOf(Pair(31.1f, 0), Pair(32.1f, 1), Pair(33.1f, 2), Pair(34.1f, 3)),
////            arrayOf(Pair(1.0f, 0), Pair(1.1f, 1), Pair(1.2f, 2), Pair(1.3f, 3), Pair(1.4f, 4))
////        )
////        for (i in values.indices)
////            for (j in values[i].indices)
////                if (radio.text.toString().toFloat() == values[i][j].first) {
////                    valuesRadioGroup[i] = values[i][j].second
////                }
////
////        val column = valuesRadioGroup[1] * 4 + valuesRadioGroup[0] + 3
////        val row = valuesRadioGroup[2] * 5 + valuesRadioGroup[3] + 2
////        radio.isChecked = true
////        compTable.onPressCell(row, column)
////    }
//}