package com.agnitt.vdt.library

import com.agnitt.vdt.*
import com.agnitt.vdt.Listener.Companion.listener
import kotlinx.android.synthetic.main.tmpl_discrete_slider.view.*
import kotlinx.android.synthetic.main.tmpl_switch_slider.view.*

class DiscreteSlider {
    fun create(
        id: Int, parent: VG?, text: String, dataList: List<Float>,
        progress: Float, sectionCount: Int = 4
    ) = ((parent inflate R.layout.tmpl_discrete_slider) as LL).apply {
        tw_slider_discrete.text = text
        slider_discrete.apply {
            this.id = id
            configBuilder.apply {
                min(dataList[0])
                max(dataList[1])
                progress(progress)
                sectionCount(sectionCount)
                if (dataList[0].toInt().toFloat() != min) {
                    showProgressInFloat()
                    floatType()
                }
                thumbRadius((thumbRadius / 1.3).toInt())
                build()
            }
            onProgressChangedListener = listener
        }
    }.apply { parent?.addView(this) }
}

class SwitchSlider {
    fun create(id: Int, parent: VG?, text: String, dataList: List<Float>, progress: Float) =
        ((parent inflate R.layout.tmpl_switch_slider) as LL).apply {
            tw_slider_switch.text = text
            slider_switch.apply {
                this.id = id
                configBuilder.apply {
//                    min(min * 10)
//                    max(max * 10)
                    min(dataList[0])
                    max(dataList[1])
                    sectionCount(1)
                    progress(if (progress > min) this.max else this.min)
                    thumbRadius((thumbRadius / 1.3).toInt())
                    build()
                }
                onProgressChangedListener = listener
            }
            tw_slider_switch_values.apply {
                tw_slider_switch_min.text = dataList[0].toString()
                tw_slider_switch_max.text = dataList[1].toString()
            }
        }.apply { parent?.addView(this) }


}
