package com.agnitt.vdt

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agnitt.vdt.DATABASE_VERSION
import com.agnitt.vdt.PageDAO
import com.agnitt.vdt.models.Chart
import com.agnitt.vdt.models.Page
import com.agnitt.vdt.models.SideItem
import com.agnitt.vdt.models.Table

@Database(
    entities = [Page::class, Chart::class, Table::class, SideItem::class],
    version = DATABASE_VERSION
)
abstract class PageDatabase : RoomDatabase() {
    abstract fun getDAO(): PageDAO
}