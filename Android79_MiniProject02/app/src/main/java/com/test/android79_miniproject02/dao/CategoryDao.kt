package com.test.android79_miniproject02.dao

import android.content.ContentValues
import android.content.Context
import com.test.android79_miniproject02.dao.DBHelper.Companion.CATEGORY_COLUMN_ID
import com.test.android79_miniproject02.dao.DBHelper.Companion.CATEGORY_COLUMN_NAME
import com.test.android79_miniproject02.dao.DBHelper.Companion.TABLE_CATEGORY
import com.test.android79_miniproject02.data.Category

class CategoryDao{

    companion object {
        fun addCategory(context: Context, categoryName: String) {
            val db = DBHelper(context).writableDatabase

            val values = ContentValues().apply {
                put(CATEGORY_COLUMN_NAME, categoryName)
            }
            db.insert(TABLE_CATEGORY, null, values)
            db.close()
        }

        fun selectCategory(context: Context, name: String): Category? {
            val db = DBHelper(context).writableDatabase

            val selectionClause = "$CATEGORY_COLUMN_NAME = ?"
            val selectionArgs = arrayOf(name)
            val cursor =
                db.query(TABLE_CATEGORY, null, selectionClause, selectionArgs, null, null, null)

            var category: Category? = null

            if (cursor.moveToNext()) {
                val idxName = cursor.getColumnIndex(CATEGORY_COLUMN_NAME)
                val categoryName = cursor.getString(idxName)

                category = Category(categoryName)
            }
            cursor.close()
            db.close()

            return category
        }

        fun selectCategoryId(context: Context, name: String): Int {
            val db = DBHelper(context).writableDatabase

            val selectionClause = "$CATEGORY_COLUMN_NAME = ?"
            val selectionArgs = arrayOf(name)
            val cursor =
                db.query(TABLE_CATEGORY, null, selectionClause, selectionArgs, null, null, null)

            var id = -1

            if (cursor.moveToNext()) {
                val idxIndex = cursor.getColumnIndex(CATEGORY_COLUMN_ID)
                val categoryIndex = cursor.getInt(idxIndex)

                id = categoryIndex
            }
            cursor.close()
            db.close()

            return id
        }

        fun getAllCategories(context: Context): List<Category> {
            val db = DBHelper(context).writableDatabase
            val cursor = db.query(TABLE_CATEGORY, null, null, null, null, null, null)
            val categoryList = mutableListOf<Category>()

            while (cursor.moveToNext()) {
                val idxName = cursor.getColumnIndex(CATEGORY_COLUMN_NAME)
                val categoryName = cursor.getString(idxName)

                val category = Category(categoryName)
                categoryList.add(category)
            }
            cursor.close()
            db.close()


            return categoryList
        }

        fun updateCategory(context: Context, oldName: String, newName: String) {
            val db = DBHelper(context).writableDatabase
            val values = ContentValues().apply {
                put(CATEGORY_COLUMN_NAME, newName)
            }
            val whereClause = "$CATEGORY_COLUMN_NAME = ?"
            val whereArgs = arrayOf(oldName)
            db.update(TABLE_CATEGORY, values, whereClause, whereArgs)
            db.close()

        }

        fun deleteCategory(context: Context, name: String) {
            val db = DBHelper(context).writableDatabase
            val whereClause = "$CATEGORY_COLUMN_NAME = ?"
            val whereArgs = arrayOf(name)

            db.delete(TABLE_CATEGORY, whereClause, whereArgs)
            db.close()
        }
    }

}