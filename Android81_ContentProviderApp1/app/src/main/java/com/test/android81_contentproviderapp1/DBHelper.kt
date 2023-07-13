package com.test.android81_contentproviderapp1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    companion object {
        const val DB_NAME = "Test.db"
        const val TABLE_NAME = "TestTable"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = """
            create table $TABLE_NAME (
                idx integer primary key autoincrement,
                textData text not null,
                intData integer not null,
                doubleData real not null,
                dateData date not null
            )
        """.trimIndent()
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}