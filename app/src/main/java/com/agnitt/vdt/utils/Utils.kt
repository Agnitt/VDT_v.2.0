package com.agnitt.vdt.utils

import android.app.ActionBar
import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import com.agnitt.vdt.R
import com.agnitt.vdt.utils.Utils.Companion.ACT
import com.agnitt.vdt.utils.Utils.Companion.APP
import kotlin.math.pow
import kotlin.math.roundToInt

class Utils : Application() {
    init {
        APP = this
    }

    companion object {
        lateinit var APP: Application
        lateinit var ACT: Activity
    }
}

fun <T> Any?.cast() = this as? T

fun Any?.toast() {
    Toast.makeText(APP, this.toString(), Toast.LENGTH_LONG).show()
}

infix fun VG?.inflate(id: Int) =
    (APP.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        .inflate(id, this, false)

infix fun VG?.inflateMaterial(id: Int) =
    (View.inflate(ContextThemeWrapper(APP, R.style.AppTheme), id, this) as LL).getChildAt(0)

inline fun <reified T> get(id: Int): T = when (T::class) {
    String::class -> ACT.resources.getString(id) as T
    Int::class ->
        (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ACT.resources.getColor(id, APP.theme)
        else ACT.resources.getColor(id)) as T
    Drawable::class -> ACT.resources.getDrawable(id, APP.theme) as T
    else -> ACT.findViewById<View>(id) as T
}

infix fun String.of(src: String) = src.contains(this, true)
infix fun Float.round(precision: Int) =
    (this * (10F).pow(precision)).roundToInt() / (10F).pow(precision)

fun getUniqueID(): Int {
    val id = SystemClock.currentThreadTimeMillis().toInt()
    return if (id < 20000) id + 20000 else id
}

// параметры LP
const val llMatchParent = LL_LP.MATCH_PARENT
const val llWrapContent = LL_LP.WRAP_CONTENT
const val vgMatchParent = LL_LP.MATCH_PARENT
const val vgWrapContent = LL_LP.WRAP_CONTENT

// set/change LP
fun LL.hide() = this.apply { layoutParams = LL_LP(llMatchParent, 0) }
fun LL.open() = this.apply { layoutParams = LL_LP(llMatchParent, llMatchParent) }
fun VG_LP.margins(L: Int, R: Int, T: Int, B: Int) =
    ViewGroup.MarginLayoutParams(this).apply { setMargins(L, T, R, B) }

fun LL_LP.margins(L: Int, R: Int, T: Int, B: Int) = this.apply { setMargins(L, T, R, B) }