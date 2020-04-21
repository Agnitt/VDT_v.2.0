package com.agnitt.vdt.library

import android.view.View
import com.agnitt.vdt.*

class PopupMenu {
    fun create(idParent: Int, text: String, parent: VG?, vararg content: View?) =
        ((parent inflate R.layout.tmpl_popup) as LL).apply {
            tag = text
            val idBody = getUniqueID()
            this.id = idParent
            val iconButton = IconButton().create(
                idParent, this, text, idBody,
                get(R.drawable.arrow_down)
            )

            addView(((this inflate R.layout.content_popup) as LL).apply {
                id = idBody
                this.tag = idParent

                background = get(R.color.popupMenuButtonBack)
                content.forEach { x ->
                    if (x != null) {
                        x.setBackgroundColor(get(R.color.popupMenuButtonBack))
                        this.setBackgroundColor(get(R.color.popupMenuButtonBack))
                        this.addView(x)
                    }
                    this.addView(
                        TextView().create(
                            getUniqueID() + 50000,
                            null,
                            "G",
                            R.color.popupMenuButtonBack
                        )
                    )
                }
                this.hide()
            })

            iconButton.setOnClickListener { v ->
                val popupBody = get<LL>(v.tag.toString().toInt())
                val popup = get<LL>(v.id)

                if (popupBody.height == 0) {
                    (v as MB).popupOpen()
                    popup.background = get(R.color.popupMenuButtonBack)
                    popupBody.open()
                } else {
                    (v as MB).popupHide()
                    popup.background = get(android.R.color.transparent)
                    popupBody.hide()
                }
            }
        }.apply { parent?.addView(this) }
}