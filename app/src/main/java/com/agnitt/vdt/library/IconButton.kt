package com.agnitt.vdt.library

import android.graphics.drawable.Drawable
import com.agnitt.vdt.*
import com.google.android.material.button.MaterialButton

class IconButton {
    fun create(id: Int, parent: VG?, text: String, tag: Any, icon: Drawable) =
        ((parent inflateMaterial R.layout.tmpl_icon_button) as MB).apply {
            this.id = id
            this.tag = tag
            this.text = text
            iconEnd(icon)
            (this.parent as LL).removeView(this)
        }.apply { parent?.addView(this) }
}

fun MaterialButton.iconEnd(icon: Drawable) {
    this.icon = icon
    iconPadding = 5
    iconGravity = MaterialButton.ICON_GRAVITY_END
    iconSize = 13
}

fun MaterialButton.popupHide() {
    icon = get(R.drawable.arrow_down)
    iconPadding = 5
    iconGravity = MaterialButton.ICON_GRAVITY_END
    iconSize = 13
    cornerRadius = 8
}

fun MaterialButton.popupOpen() {
    icon = get(R.drawable.arrow_up)
    iconPadding = 5
    iconGravity = MaterialButton.ICON_GRAVITY_END
    iconSize = 13
    cornerRadius = 0
}

