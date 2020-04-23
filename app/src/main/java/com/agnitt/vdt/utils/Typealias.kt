package com.agnitt.vdt.utils

import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.xw.repo.BubbleSeekBar

typealias ArrDataSet = Array<Pair<Float, Boolean>>
typealias ArrLabelsSet = Array<Pair<String, String>>

typealias VG = ViewGroup
typealias LL = LinearLayout
typealias CL = ConstraintLayout
typealias FL = FrameLayout
typealias TV = TextView
typealias RG = RadioGroup
typealias RB = RadioButton
typealias MB = MaterialButton
typealias Sw = Switch
typealias BSB = BubbleSeekBar
typealias VG_LP = ViewGroup.LayoutParams
typealias CL_LP = ConstraintLayout.LayoutParams
typealias LL_LP = LinearLayout.LayoutParams
typealias RL_LP = RelativeLayout.LayoutParams
typealias Formula = (Int, Float) -> Float