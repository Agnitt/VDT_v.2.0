package com.agnitt.vdt.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.agnitt.vdt.R
import com.agnitt.vdt.builders.*
import com.agnitt.vdt.builders.PageBuilder.Companion.pageBuilder
import com.agnitt.vdt.data.Parser
import com.agnitt.vdt.data.Saver
import testPageFill

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Parser()
//            .init()
        PageBuilder(this)
        Saver()

        testPageFill()

//        pages.joinToString("\n", "[PAGES]\n", "[END]", transform = { it.toString() }).log()

        pageBuilder.init()

//        Parser().init()

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

//    fun testFill() {
//        tableBuilder.init().buildDashboard(
//            listOf(Table(3974, 10, MutableList(12 * 20) { Random.nextInt(983, 1012).toFloat() }))
//        )
////    sideMenuBuilder.init().buildSideMenu()
//
////        chartsBuilder.init().buildDashboard(
////            listOf(
////                Chart(getUniqueID().toLong(), 2, "hfsgjsh", "%", Types.BIG.name, randomList(5,5, 20), randomList(5,10, 40), Random.nextInt(1, 50).toFloat()),
////                Chart(getUniqueID().toLong(), 2, "rger", "%", Types.SMALL.name, randomList(5,5, 20), randomList(5,10, 40), Random.nextInt(1, 50).toFloat()),
////                Chart(getUniqueID().toLong(), 2, "het", "%", Types.SMALL.name, randomList(5,5, 20), randomList(5,10, 40), Random.nextInt(1, 50).toFloat()),
////                Chart(getUniqueID().toLong(), 2, "thjktljik", "%", Types.SMALL.name, randomList(5,5, 20), randomList(5,10, 40), Random.nextInt(1, 50).toFloat()),
////                Chart(getUniqueID().toLong(), 2, "liefi", "%", Types.SMALL.name, randomList(5,5, 20), randomList(5,10, 40), Random.nextInt(1, 50).toFloat()),
////                Chart(getUniqueID().toLong(), 2, "iefhlesj", "%", Types.SMALL.name, randomList(5,5, 20), randomList(5,10, 40), Random.nextInt(1, 50).toFloat()),
////                Chart(getUniqueID().toLong(), 2, "kjdn", "%", Types.SMALL.name, randomList(5,5, 20), randomList(5,10, 40), Random.nextInt(1, 50).toFloat())
////            )
////        )
//        sideMenuBuilder.init().buildSideMenu(
//            listOf(
//                SideItem(
//                    getUniqueID().toLong(),
//                    1,
//                    "dis slider",
//                    "Discrete_Slider".toUpperCase(),
//                    listOf(1f, 80f),
//                    1f
//                ),
//                SideItem(
//                    getUniqueID().toLong(),
//                    1,
//                    "Hello",
//                    "Switch".toUpperCase(),
//                    listOf(0f, 1f),
//                    0f
//                ),//
////                SideItem(getUniqueID().toLong(),1,"fhdf","Radio_Group".toUpperCase(),listOf(1f, 2f, 3f),2f),
//                SideItem(
//                    getUniqueID().toLong(),
//                    1,
//                    "hkwjek",
//                    "Switch_Slider".toUpperCase(),
//                    listOf(0.4f, 0.6f),
//                    0.4f
//                ),
//                SideItem(
//                    getUniqueID().toLong(),
//                    1,
//                    "jdl;wjsldcksjn",
//                    "Discrete_Slider".toUpperCase(),
//                    listOf(1f, 9f),
//                    7f
//                ),
//                SideItem(
//                    getUniqueID().toLong(),
//                    1,
//                    "ljdhmlkwp dmi qwo",
//                    "Discrete_Slider".toUpperCase(),
//                    listOf(100f, 200f),
//                    125f
//                )
////                SideItem(getUniqueID().toLong(), 1, "ekjgk", "Text_View".toUpperCase(), listOf())
//            )
//        )
//    }
}
