package com.agnitt.vdt.library

import android.graphics.drawable.Drawable
import com.agnitt.vdt.R
import com.agnitt.vdt.utils.*
import com.google.android.material.button.MaterialButton

class IconButton {
    init {
        ib = this
    }

    companion object {
        lateinit var ib: IconButton
    }

    fun create(position: Int?, id: Int, parent: VG?, text: String, tag: Any, icon: Drawable) =
        ((parent inflateMaterial R.layout.tmpl_icon_button) as MB).apply {
            this.id = id
            this.tag = tag
            this.text = text
            iconEnd(icon)
            (this.parent as LL).removeView(this)
        }.apply { parent?.add(this, position) }
}

fun MaterialButton.iconEnd(icon: Drawable) {
    this.icon = icon
    iconPadding = 5
    iconGravity = MaterialButton.ICON_GRAVITY_END
    iconSize = 13
}

fun MaterialButton.popupHide() {
    icon = com.agnitt.vdt.utils.get(R.drawable.arrow_down)
    iconPadding = 5
    iconGravity = MaterialButton.ICON_GRAVITY_END
    iconSize = 13
    cornerRadius = 8
}

fun MaterialButton.popupOpen() {
    icon = com.agnitt.vdt.utils.get(R.drawable.arrow_up)
    iconPadding = 5
    iconGravity = MaterialButton.ICON_GRAVITY_END
    iconSize = 13
    cornerRadius = 0
}

