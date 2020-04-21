package com.agnitt.vdt.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.agnitt.vdt.Listener
import com.agnitt.vdt.R
import com.agnitt.vdt.Utils
import com.agnitt.vdt.getUniqueID
import com.agnitt.vdt.library.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.side_menu.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Listener()
        Utils.apply {
            ACT = this@MainActivity
        }

        PopupMenu().create(
            getUniqueID(),
            "afheklh",
            body_menu,
            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText),
            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText)
        )
        PopupMenu().create(
            getUniqueID(),
            "afheklh",
            body_menu,
            DiscreteSlider().create(getUniqueID(), null, "dis slider", listOf(1f, 80f), 1f),
            Switch().create(getUniqueID(), null, "Hello", true),
            RadioGroup().create(getUniqueID(), null, "fhdf", listOf(1f, 2f, 3f), 2),
            SwitchSlider().create(getUniqueID(), null, "hkwjek", listOf(0.4f, 0.6f), 0.5f),
            TextView().create(getUniqueID(), null, "ekjgk", R.color.popupMenuButtonText)
        )
        Log.d("LOG", "main")

    }

}
