package com.agnitt.vdt

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.graphics.drawable.Drawable
import kotlinx.android.synthetic.main.tmpl_icon_button.view.*

//
//fun dSlider(
//    id: Int, parent: LL, text: String, min: Float, max: Float,
//    progressDefault: Float = min, sectionCount: Int = 4, isFloat: Boolean = false
//) = inflater(R.layout.slider_discrete, parent).apply {
//    tw_slider_discrete.text = text
//    slider_discrete.apply {
//        tag = text
//        this.id = id
//        configBuilder.apply {
//            val progress =
//                if (getStatePageElem(id) != 0f) min + (getStatePageElem(id)) else progressDefault
//            min(min)
//            max(max)
//            progress(progress)
//            setStateItem(id, progress - min)
//            sectionCount(sectionCount)
//            if (min.toInt().toFloat() != min || isFloat) {
//                showProgressInFloat()
//                floatType()
//            }
//            thumbRadius((thumbRadius / 1.3).toInt())
//            build()
//        }
//        SideMenu.arrPagesElem.add(id)
//        onProgressChangedListener = listener
//    }
//    layoutParams.margins(10, 10, 10, 10)
//}!!
//
//fun swSlider(id: Int, parent: LL, text: String, min: Float, max: Float) =
//    inflater(R.layout.slider_switch, parent).apply {
//        tw_slider_switch.text = text
//        slider_switch.apply {
//            tag = "switch"
//            this.id = id
//            configBuilder.apply {
//                min(min * 10)
//                max(max * 10)
//                val progress = (getStatePageElem(id) * 10) + min
//
//                sectionCount(1)
//                progress(if (progress > min) this.max else this.min)
//                setStateItem(id, (this.progress - this.min) / 10)
//                thumbRadius((thumbRadius / 1.3).toInt())
//                build()
//            }
//            SideMenu.arrPagesElem.add(id)
//            onProgressChangedListener = listener
//        }
//        tw_slider_switch_values.apply {
//            tw_slider_switch_min.text = min.toString()
//            tw_slider_switch_max.text = max.toString()
//            layoutParams.margins(5, 5, -18, 0)
//        }
//        layoutParams.margins(10, 10, 10, 10)
//    }!!
//

