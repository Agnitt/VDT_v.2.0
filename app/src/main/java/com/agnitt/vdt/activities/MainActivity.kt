package com.agnitt.vdt.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.*
import com.agnitt.vdt.data.Parser

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PageBuilder(this)
        Parser().init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        AppToolbar.menu = menu!!
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
