package com.agnitt.vdt.data

import android.content.Context
import com.agnitt.vdt.builders.AppToolbar
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.builders.PageBuilder.Companion.openPage
import com.agnitt.vdt.builders.PageBuilder.Companion.pageBuilder
import com.agnitt.vdt.builders.SideMenuBuilder.Companion.sideMenuBuilder
import com.agnitt.vdt.builders.saveParameters
import com.agnitt.vdt.data.Saver.Companion.editor
import com.agnitt.vdt.data.Saver.Companion.isSave
import com.agnitt.vdt.library.RadioGroup.Companion.rg
import com.agnitt.vdt.models.Types
import com.agnitt.vdt.utils.*

class Saver {
    init {
        saver = this
        preferences = ACT.getSharedPreferences("saverDB", Context.MODE_PRIVATE)
        editor = preferences.edit()
        if (preferences.all.isNotEmpty() && AppToolbar.menu?.getItem(2) != null)
            saveParameters(AppToolbar.menu!!.getItem(2))
    }

    companion object {
        lateinit var saver: Saver
        lateinit var preferences: SP
        lateinit var editor: SP_E
        var permanentSavingOn: Boolean = false
        var isSave: Boolean = false
    }
}

inline fun <reified T> SP.get(key: String): T? = when (T::class) {
    Float::class -> getFloat(key, -1f) as T
    Boolean::class -> getBoolean(key, false) as T
    String::class -> getString(key, null) as T?
    Int::class -> getInt(key, -1) as T
    Long::class -> getLong(key, -1) as T
    Set::class -> getStringSet(key, null) as T?
    Map::class -> all as T
    else -> null
}.run { if (this == null || this == -1 || this == -1f) return@run null else return@run this }

inline fun <reified T> SP_E.put(key: String, value: T) = when (value) {
    is Float -> putFloat(key, value)
    is Boolean -> putBoolean(key, value)
    is String -> putString(key, value)
    is Int -> putInt(key, value)
    is Long -> putLong(key, value)
    is MutableSet<*> -> putStringSet(key, value as MutableSet<String>)
    else -> null
}

inline fun <reified T> SP_E.save(key: String, value: T) = this.apply {
    put(key, value)
    apply()
}

fun SP_E.delete(key: String) = this.apply {
    remove(key)
    apply()
}

fun SP_E.deleteAll() = this.apply {
    clear()
    apply()
}

fun saveState() = pageBuilder.getPageByName(openPage)?.sideItems?.forEachIndexed { i, item ->
    when {
        item.type.contains("SLIDER") -> get<BSB>(item.sideItemId.toInt()).apply {
            editor.save(this.id.toString(), this.progressFloat)
        }
        item.type.contains("SWITCH") -> get<Sw>(item.sideItemId.toInt()).apply {
            editor.save(this.id.toString(), this.isChecked)
        }
        item.type.contains("RADIO") -> sideMenuBuilder.getTableSideMenuItem(i - 1)?.apply {
            editor.save(this.id.toString(), this.checkedRadioButtonId)
        }
    }
    isSave = true
}

fun removeState() {
    val items = sideMenuBuilder.items ?: return
    val type = pageBuilder.getPageByName(openPage)?.type ?: return
    if (isSave) items.forEach { editor.delete(it.sideItemId.toString()) }
    if (type == Types.TABLE.name) rg.resetcheckedIndexesRG()
    sideMenuBuilder.buildSideMenu(items, type)
    isSave = false
}

fun removePermanentSavingState() {
    rg.resetcheckedIndexesRG()
    removeState()
    editor.deleteAll()
}