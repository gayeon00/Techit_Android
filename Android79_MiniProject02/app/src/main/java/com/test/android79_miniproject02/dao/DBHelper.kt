package com.test.android79_miniproject02.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "MemoDatabase"
        const val DATABASE_VERSION = 1

        const val TABLE_MEMO = "memo"
        const val MEMO_COLUMN_ID = "memo_id"
        const val MEMO_COLUMN_CATEGORY_ID = "memo_category_id"
        const val MEMO_COLUMN_TITLE = "title"
        const val MEMO_COLUMN_CONTENT = "content"
        const val MEMO_COLUMN_DATE = "date"

        const val TABLE_CATEGORY = "category"
        const val CATEGORY_COLUMN_ID = "category_id"
        const val CATEGORY_COLUMN_NAME = "name"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val memoSQL = """CREATE TABLE $TABLE_MEMO (${MEMO_COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                $MEMO_COLUMN_CATEGORY_ID INTEGER NOT NULL,
                $MEMO_COLUMN_TITLE TEXT NOT NULL,
                $MEMO_COLUMN_CONTENT TEXT,
                $MEMO_COLUMN_DATE TEXT,
                FOREIGN KEY (${MEMO_COLUMN_CATEGORY_ID}) REFERENCES $TABLE_CATEGORY (${CATEGORY_COLUMN_ID}))"""
            .trimIndent()
        p0?.execSQL(memoSQL)

        val sql = """CREATE TABLE $TABLE_CATEGORY
                    ($CATEGORY_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $CATEGORY_COLUMN_NAME TEXT NOT NULL)"""
            .trimIndent()
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}