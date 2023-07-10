package com.test.android80_miniproject03.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "ShoppingMallDatabase"
        const val DATABASE_VERSION = 1

        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_USER_EMAIL = "user_email"
        const val COLUMN_USER_PASSWORD = "user_password"
        const val COLUMN_USER_NAME = "user_name"
        const val COLUMN_USER_USER_NAME = "user_user_name"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = """create table $TABLE_USER
            |($COLUMN_USER_ID integer primary key autoincrement,
            |$COLUMN_USER_EMAIL text not null,
            |$COLUMN_USER_PASSWORD text not null,
            |$COLUMN_USER_NAME text not null,
            |$COLUMN_USER_USER_NAME text not null)
        """.trimMargin()
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}