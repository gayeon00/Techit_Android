package com.test.android80_miniproject03.db

import android.content.Context
import com.test.android80_miniproject03.db.DBHelper.Companion.COLUMN_USER_EMAIL
import com.test.android80_miniproject03.db.DBHelper.Companion.COLUMN_USER_NAME
import com.test.android80_miniproject03.db.DBHelper.Companion.COLUMN_USER_PASSWORD
import com.test.android80_miniproject03.db.DBHelper.Companion.COLUMN_USER_USER_NAME
import com.test.android80_miniproject03.db.DBHelper.Companion.TABLE_USER
import com.test.android80_miniproject03.user.User

class UserDao {
    companion object {
        fun isEmailExists(context: Context, email: String): Boolean {
            val db = DBHelper(context).writableDatabase

            val query = "SELECT COUNT(*) FROM $TABLE_USER WHERE $COLUMN_USER_EMAIL = ?"
            val cursor = db.rawQuery(query, arrayOf(email))

            cursor.moveToFirst()
            val count = cursor.getInt(0)
            cursor.close()
            db.close()
            return count > 0
        }

        fun isPasswordCorrect(context: Context, email: String, password: String): Boolean {
            val db = DBHelper(context).writableDatabase

            val query =
                "SELECT COUNT(*) FROM $TABLE_USER WHERE $COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
            val cursor = db.rawQuery(query, arrayOf(email, password))
            cursor.moveToFirst()
            val count = cursor.getInt(0)
            cursor.close()
            return count > 0
        }

        fun addUser(context: Context, user: User) {
            val db = DBHelper(context).writableDatabase
            val query = """insert into $TABLE_USER
                |($COLUMN_USER_EMAIL, $COLUMN_USER_PASSWORD, $COLUMN_USER_NAME, $COLUMN_USER_USER_NAME)
                |values (?,?,?,?)
            """.trimMargin()

            val arg1 = arrayOf(
                user.email,
                user.password,
                user.name,
                user.userName
            )
            db.execSQL(query, arg1)
            db.close()
        }
    }
}