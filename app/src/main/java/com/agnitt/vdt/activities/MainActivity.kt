package com.agnitt.vdt.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.TableBuilder
import com.agnitt.vdt.builders.flipSideMenu
import com.agnitt.vdt.builders.hideSideMenu
import com.agnitt.vdt.builders.saveParameters
import com.agnitt.vdt.data.Listener
import com.agnitt.vdt.utils.Utils
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Listener()
        Utils.apply {
            ACT = this@MainActivity
        }



//        TableBuilder(MutableList(12 * 20) { Random.nextInt(983, 1012).toString() }).init().build()

//        PopupMenu().create(
//            getUniqueID(),
//            "afheklh",
//            body_menu,
//            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
//            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
//            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
//            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
//            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
//            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText)
//        )
//        PopupMenu().create(
//            getUniqueID(),
//            "afheklh",
//            body_menu,
//            DiscreteSlider().create(getUniqueID(), null, "dis slider", listOf(1f, 80f), 1f),
//            Switch().create(getUniqueID(), null, "Hello", true),
//            RadioGroup().create(getUniqueID(), null, "fhdf", listOf(1f, 2f, 3f), 2),
//            SwitchSlider().create(getUniqueID(), null, "hkwjek", listOf(0.4f, 0.6f), 0.5f),
//            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText)
//        )
//        Log.d("LOG", "main")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.i_flip_side_menu -> flipSideMenu(item)
            R.id.i_hide_side_menu -> hideSideMenu(item)
            R.id.i_save_parameters_side_menu -> saveParameters(item)
        }
        return super.onOptionsItemSelected(item)
    }

}
