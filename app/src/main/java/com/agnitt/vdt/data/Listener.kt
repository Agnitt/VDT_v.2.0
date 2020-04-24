package com.agnitt.vdt.data

import com.agnitt.vdt.data.Parser.Companion.parser
import com.agnitt.vdt.data.Saver.Companion.editor
import com.agnitt.vdt.data.Saver.Companion.permanentSavingOn
import com.agnitt.vdt.utils.BSB
import com.agnitt.vdt.utils.RG
import com.agnitt.vdt.utils.Sw
import com.xw.repo.BubbleSeekBar

class Listener : BubbleSeekBar.OnProgressChangedListener {
    init {
        listener = this
    }

    companion object {
        lateinit var listener: Listener
    }

    override fun onProgressChanged(slider: BSB?, pI: Int, pF: Float, fromUser: Boolean) =
        touchSeekBar(slider!!, pF)

    override fun getProgressOnActionUp(slider: BSB?, pI: Int, pF: Float) =
        touchSeekBar(slider!!, pF)

    override fun getProgressOnFinally(slider: BSB?, pI: Int, pF: Float, fromUser: Boolean) =
        touchSeekBar(slider!!, pF)
}

fun touchSeekBar(slider: BSB, progressFloat: Float) {
    slider.permanentSavingState()
    parser.getReaction(slider.id, progressFloat)
}

fun BSB.permanentSavingState() {
    if (permanentSavingOn) editor.save(id.toString(), this.progressFloat)
}

fun Sw.permanentSavingState() {
    if (permanentSavingOn) editor.save(id.toString(), this.isChecked)
}

fun RG.permanentSavingState() {
    if (permanentSavingOn) editor.save(id.toString(), this.checkedRadioButtonId)
}
