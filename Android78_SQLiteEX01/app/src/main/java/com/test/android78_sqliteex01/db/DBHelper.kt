package com.test.android78_sqliteex01.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Students.db", null, 1) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        val sql="""create table StudentTable
            (idx integer primary key autoincrement,
            name text not null,
            age integer not null,
            kor integer not null)
        """.trimIndent()

        //쿼리문 수행
        sqLiteDatabase?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}