package com.agnitt.vdt.builders

import android.app.ActionBar
import android.graphics.drawable.Drawable
import android.view.MenuItem
import com.agnitt.vdt.R
import com.agnitt.vdt.utils.get

fun ActionBar.setHome() = this.apply {
    setDisplayHomeAsUpEnabled(true)
    setIcon(R.drawable.home)
    setDisplayUseLogoEnabled(true)
    setTitle(R.string.app_name)
    show()
}

fun flipSideMenu(item: MenuItem) {
    item.setState(R.drawable.ic_flip_yellow_24dp, R.drawable.ic_flip_gray_24dp)
}

fun hideSideMenu(item: MenuItem) {
    item.setState(R.drawable.ic_show_orange_24dp, R.drawable.ic_show_gray_24dp)
}

fun saveParameters(item: MenuItem) {
    item.setState(R.drawable.ic_star_orange_24dp, R.drawable.ic_star_border_gray_24dp)
}

fun MenuItem.setState(on: Int, off: Int) {
    val drawOn = get<Drawable>(on)
    val drawOff = get<Drawable>(off)
    icon = if (this.isChecked) drawOff else drawOn
    this.isChecked = !this.isChecked
}