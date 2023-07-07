package com.test.android79_miniproject02.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.test.android79_miniproject02.data.Category

class CategoryDao(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MemoDatabase"
        private const val DATABASE_VERSION = 1

        private const val TABLE_CATEGORY = "category"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        val sql = """CREATE TABLE $TABLE_CATEGORY
                    ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_NAME TEXT NOT NULL)"""
            .trimIndent()
        sqLiteDatabase?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addCategory(categoryName: String) {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME, categoryName)
        }
        db.insert(TABLE_CATEGORY, null, values)
        db.close()
    }

    fun getAllCategories(): List<Category> {
        val db = writableDatabase
        val cursor = writableDatabase.query(TABLE_CATEGORY, null, null, null, null, null, null)
        val categoryList = mutableListOf<Category>()

        while (cursor.moveToNext()) {
            val idxName = cursor.getColumnIndex(COLUMN_NAME)
            val categoryName = cursor.getString(idxName)

            val category = Category(categoryName)
            categoryList.add(category)
        }
        cursor.close()
        db.close()

        return categoryList
    }

}