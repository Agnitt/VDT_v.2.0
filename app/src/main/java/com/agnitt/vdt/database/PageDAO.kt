package com.agnitt.vdt.database


import androidx.room.*
import com.agnitt.vdt.models.Chart
import com.agnitt.vdt.models.Page
import com.agnitt.vdt.models.SideItem
import com.agnitt.vdt.models.Table

@Dao
interface PageDAO {
    /**  CRUD :
     **
     **  create **/

    @Insert
    fun insert(page: Page): Long

    @Transaction
    fun insertFullPage(page: Page) {
        insert(page)
        page.mainItemsIds = getMainItemsIdsForPage(page.id)
        page.sideItemsIds = getSideItemsIdsForPage(page.id)
        update(page)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chart: Chart): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg charts: Chart): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(table: Table): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sideItem: SideItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg sideItem: SideItem): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(page: Page, charts: List<Chart>, sideItems: List<SideItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(page: Page, table: Table, sideItems: List<SideItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(page: Page, table: Table)

    /** read **/

    @Transaction
    @Query(GET + TABLE_OF_PAGES)
    fun getAllPages(): List<Page>?

    @Transaction
    @Query(GET + TABLE_OF_PAGES + BY_ID)
    fun getPageByID(id: Long): Page?

    @Transaction
    @Query(GET + TABLE_OF_PAGES + BY_NAME)
    fun getPageByName(name: String): Page?

    @Transaction
    fun getMainItemsIdsForPage(pageId: Long) = mutableListOf<Long>().apply {
        getChartsByOwner(pageId)?.forEach { this.add(it.chartId) }
        getTablesByOwner(pageId)?.forEach { this.add(it.tableId) }
    }

    @Transaction
    fun getSideItemsIdsForPage(pageId: Long) = mutableListOf<Long>()
        .apply { getSideItemsByOwner(pageId)?.forEach { this.add(it.sideItemId) } }

    @Transaction
    @Query(GET + TABLE_OF_CHARTS + CHART_BY_ID)
    fun getChartByID(id: Long): Chart?

    @Transaction
    @Query(GET + TABLE_OF_CHARTS + BY_OWNER)
    fun getChartsByOwner(ownerId: Long): List<Chart>?

    @Transaction
    @Query(GET + TABLE_OF_CHARTS + BY_NAME_AND_OWNER)
    fun getChartsByNameAndOwner(name: String, ownerId: Long): List<Chart>?

    @Transaction
    @Query(GET + TABLE_OF_TABLES + TABLE_BY_ID)
    fun getTableByID(id: Long): Table?

    @Transaction
    @Query(GET + TABLE_OF_TABLES + BY_OWNER)
    fun getTablesByOwner(ownerId: Long): List<Table>?

//    @Transaction
//    @Query(GET + TABLE_OF_TABLES + BY_NAME_AND_OWNER)
//    fun getTablesByNameAndOwner(name: String, ownerId: Long): List<Table>?

    @Transaction
    @Query(GET + TABLE_OF_SIDE_ITEMS + SIDE_ITEM_BY_ID)
    fun getSideItemByID(id: Long): SideItem?

    @Transaction
    @Query(GET + TABLE_OF_SIDE_ITEMS + BY_OWNER)
    fun getSideItemsByOwner(ownerId: Long): List<SideItem>?

    @Transaction
    @Query(GET + TABLE_OF_SIDE_ITEMS + BY_NAME_AND_OWNER)
    fun getSideItemsByNameAndOwner(name: String, ownerId: Long): List<SideItem>?

    /** update **/
    @Transaction
    fun updateIdsPage(page: Page) {
        page.mainItemsIds = getMainItemsIdsForPage(page.id)
        page.sideItemsIds = getSideItemsIdsForPage(page.id)
        update(page)
    }

    @Update
    fun update(page: Page)

    @Update
    fun update(chart: Chart)

    @Update
    fun update(table: Table)

    @Update
    fun update(sideItem: SideItem)

    /** delete **/
    @Transaction
    fun deletePage(page: Page) {
        getChartsByOwner(page.id)?.forEach { delete(it) }
        getTablesByOwner(page.id)?.forEach { delete(it) }
        getSideItemsByOwner(page.id)?.forEach { delete(it) }
        delete(page)
    }

    @Delete
    fun delete(page: Page)

    @Delete
    fun delete(chart: Chart)

    @Delete
    fun delete(table: Table)

    @Delete
    fun delete(sideItem: SideItem)

    /** usefull funs **/

}