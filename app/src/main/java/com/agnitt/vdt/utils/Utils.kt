package com.agnitt.vdt.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.data.Saver.Companion.preferences
import com.agnitt.vdt.utils.Utils.Companion.APP
import com.agnitt.vdt.utils.Utils.Companion.YEAR
import com.github.mikephil.charting.data.Entry
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

class Utils : Application() {
    init {
        APP = this
    }

    companion object {
        lateinit var APP: Application
        val YEAR = Calendar.getInstance().get(Calendar.YEAR)
    }
}

fun Any.log() = Log.d("LOG", "${this}\n")

var randomList = { size: Int, start: Int, end: Int ->
    mutableListOf<Float>().apply {
        for (i in 0 until size) add(Random.nextInt(start, end).toFloat())
    }
}

fun <T> Any?.cast() = this as? T

fun Any?.toast() {
    Toast.makeText(APP, this.toString(), Toast.LENGTH_LONG).show()
}

infix fun <T> Boolean.so(resultIfTrue: T): T? = if (this) resultIfTrue else null

infix fun VG?.inflate(id: Int) =
    (APP.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        .inflate(id, this, false)

infix fun VG?.inflateMaterial(id: Int) =
    (View.inflate(ContextThemeWrapper(APP, R.style.AppTheme), id, this) as LL).getChildAt(0)

fun VG?.add(view: View, position: Int?) =
    if (position != null) this?.addView(view, position) else this?.addView(view)

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

fun List<Float>.toEntries(index:Int = 0) =
    mapIndexed { i, value -> Entry((i + YEAR + index).toFloat(), value) }
fun <T> MutableList<T>.addIfNotNull(item: T?) = if (item != null) add(item) else false

var tyui = 60000

fun getUniqueID(): Int {
    return  ++tyui
//    return View.generateViewId () + SystemClock.currentThreadTimeMillis().toInt()
//    return if (id < 20000) id + 20000 else id
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