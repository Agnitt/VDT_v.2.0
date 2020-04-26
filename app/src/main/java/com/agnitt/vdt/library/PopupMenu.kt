package com.agnitt.vdt.library

import android.view.View
import com.agnitt.vdt.R
import com.agnitt.vdt.library.TextView.Companion.tv
import com.agnitt.vdt.utils.*

class PopupMenu {
    init {
        pm = this
    }

    companion object {
        lateinit var pm: PopupMenu
    }

    fun create(position: Int?, idParent: Int, text: String, parent: VG?, content: List<View?>) =
        ((parent inflate R.layout.tmpl_popup) as LL).apply {
            tag = text
            val idBody = uniqueID()
            this.id = idParent
            background = get(R.color.popupMenuButtonBack)
            val iconButton = IconButton().create(
                0,
                idParent, this, text, idBody,
                get(R.drawable.arrow_up)
            ).apply{ this.popupOpen()}

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
                        tv.create(
                            null, uniqueID(), null, "G",
                            R.color.popupMenuButtonBack
                        )
                    )
                }
                this.open()
            })

            iconButton.setOnClickListener { v ->
                val popupBody =
                    get<LL>(v.tag.toString().toInt())
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
        }.apply { parent?.add(this, position) }
}