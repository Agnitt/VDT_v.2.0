package com.agnitt.vdt.builders

import android.app.ActionBar
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.ACT
import com.agnitt.vdt.data.Saver.Companion.permanentSavingOn
import com.agnitt.vdt.data.removePermanentSavingState
import com.agnitt.vdt.data.saveState
import com.agnitt.vdt.utils.CL_LP
import com.agnitt.vdt.utils.get
import kotlinx.android.synthetic.main.activity_main.*

class AppToolbar {
    companion object {
        var menu: Menu? = null
    }
}

fun ActionBar.setHome() = this.apply {
    setDisplayHomeAsUpEnabled(true)
    setIcon(R.drawable.home)
    setDisplayUseLogoEnabled(true)
    setTitle(R.string.app_name)
    show()
}

fun flipSideMenu(item: MenuItem) {
    item.setState(R.drawable.ic_flip_yellow_24dp, R.drawable.ic_flip_gray_24dp)

    val pagesLP = ACT.ll_pages.layoutParams as CL_LP
    val mainLP = ACT.cl_content_main.layoutParams as CL_LP
    val sideLP = ACT.cl_side_menu.layoutParams as CL_LP

    val changeConstraint = { itemLP: CL_LP ->
        itemLP.apply {
            startToStart = if (startToStart == CL_LP.PARENT_ID) CL_LP.UNSET else CL_LP.PARENT_ID
            endToEnd = if (endToEnd == CL_LP.PARENT_ID) CL_LP.UNSET else CL_LP.PARENT_ID
        }
    }

    changeConstraint(pagesLP)
    changeConstraint(mainLP)
    changeConstraint(sideLP)

    ACT.ll_pages.layoutParams = pagesLP
    ACT.cl_content_main.layoutParams = mainLP
    ACT.cl_side_menu.layoutParams = sideLP
}

fun hideSideMenu(item: MenuItem) {
    item.setState(R.drawable.ic_show_orange_24dp, R.drawable.ic_show_gray_24dp)

    val pagesLP = ACT.ll_pages.layoutParams as CL_LP
    val mainLP = ACT.cl_content_main.layoutParams as CL_LP
    val sideLP = ACT.cl_side_menu.layoutParams as CL_LP

    val bool = { x: CL_LP, y: Float -> x.matchConstraintPercentWidth == y }

    pagesLP.width = if (bool(pagesLP, 0.78f)) CL_LP.MATCH_PARENT else 0
    pagesLP.matchConstraintPercentWidth = if (bool(pagesLP, 0.78f)) 1f else 0.78f

    mainLP.width = if (bool(mainLP, 0.78f)) CL_LP.MATCH_PARENT else 0
    mainLP.matchConstraintPercentWidth = if (bool(mainLP, 0.78f)) 1f else 0.78f

    sideLP.matchConstraintPercentWidth = if (bool(sideLP, 0.2f)) 0f else 0.2f

    ACT.ll_pages.layoutParams = pagesLP
    ACT.cl_content_main.layoutParams = mainLP
    ACT.cl_side_menu.layoutParams = sideLP
}

fun saveParameters(item: MenuItem) {
    if (item.isChecked) removePermanentSavingState() else saveState()
    item.setState(R.drawable.ic_star_orange_24dp, R.drawable.ic_star_border_gray_24dp)
    permanentSavingOn = !permanentSavingOn
}

fun MenuItem.setState(on: Int, off: Int) {
    val drawOn = get<Drawable>(on)
    val drawOff = get<Drawable>(off)
    icon = if (this.isChecked) drawOff else drawOn
    this.isChecked = !this.isChecked
}