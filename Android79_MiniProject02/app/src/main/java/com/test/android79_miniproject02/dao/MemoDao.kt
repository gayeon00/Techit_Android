package com.test.android79_miniproject02.dao

import android.content.ContentValues
import android.content.Context
import com.test.android79_miniproject02.dao.DBHelper.Companion.CATEGORY_COLUMN_ID
import com.test.android79_miniproject02.dao.DBHelper.Companion.MEMO_COLUMN_TITLE
import com.test.android79_miniproject02.dao.DBHelper.Companion.MEMO_COLUMN_CATEGORY_ID
import com.test.android79_miniproject02.dao.DBHelper.Companion.MEMO_COLUMN_CONTENT
import com.test.android79_miniproject02.dao.DBHelper.Companion.MEMO_COLUMN_DATE
import com.test.android79_miniproject02.dao.DBHelper.Companion.MEMO_COLUMN_ID
import com.test.android79_miniproject02.dao.DBHelper.Companion.TABLE_CATEGORY
import com.test.android79_miniproject02.dao.DBHelper.Companion.TABLE_MEMO
import com.test.android79_miniproject02.data.Memo

class MemoDao {

    companion object {
        fun addMemo(context: Context,memo: Memo) {
            val db = DBHelper(context).writableDatabase
            val values = ContentValues().apply {
                put(MEMO_COLUMN_CATEGORY_ID, memo.categoryId)
                put(MEMO_COLUMN_TITLE, memo.title)
                put(MEMO_COLUMN_CONTENT, memo.content)
                put(MEMO_COLUMN_DATE, memo.date)
            }
            db.insert(TABLE_MEMO, null, values)
            db.close()
        }

        fun getAllMemos(context: Context): List<Memo> {
            val memoList = mutableListOf<Memo>()
            val query =
                """SELECT $MEMO_COLUMN_ID, ${TABLE_CATEGORY}.${CATEGORY_COLUMN_ID}, $MEMO_COLUMN_TITLE, $MEMO_COLUMN_CONTENT, $MEMO_COLUMN_DATE 
                FROM $TABLE_MEMO
                JOIN $TABLE_CATEGORY ON $MEMO_COLUMN_CATEGORY_ID = ${TABLE_CATEGORY}.${CATEGORY_COLUMN_ID}""".trimIndent()
            val db = DBHelper(context).writableDatabase
            val cursor = db.rawQuery(query, null)

            while (cursor.moveToNext()) {
                val idxCategoryID = cursor.getColumnIndex(MEMO_COLUMN_CATEGORY_ID)
                val idxTitle = cursor.getColumnIndex(MEMO_COLUMN_TITLE)
                val idxContent = cursor.getColumnIndex(MEMO_COLUMN_CONTENT)
                val idxDate = cursor.getColumnIndex(MEMO_COLUMN_DATE)

                val categoryId = cursor.getInt(idxCategoryID)
                val title = cursor.getString(idxTitle)
                val content = cursor.getString(idxContent)
                val date = cursor.getString(idxDate)
                val memo = Memo(categoryId, title, content, date)
                memoList.add(memo)
            }
            cursor.close()
            db.close()

            return memoList
        }

        fun getMemosByCategoryId(context: Context,id: Int): List<Memo> {
            val memoList = mutableListOf<Memo>()
            val query =
                """SELECT $MEMO_COLUMN_ID, ${TABLE_CATEGORY}.${CATEGORY_COLUMN_ID}, $MEMO_COLUMN_TITLE, $MEMO_COLUMN_CONTENT, $MEMO_COLUMN_DATE 
                FROM $TABLE_MEMO
                JOIN $TABLE_CATEGORY ON $MEMO_COLUMN_CATEGORY_ID = ${TABLE_CATEGORY}.${CATEGORY_COLUMN_ID}
                WHERE $MEMO_COLUMN_CATEGORY_ID = $id""".trimIndent()

            val db = DBHelper(context).writableDatabase

            val cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val idxCategoryID = cursor.getColumnIndex(MEMO_COLUMN_CATEGORY_ID)
                val idxTitle = cursor.getColumnIndex(MEMO_COLUMN_TITLE)
                val idxContent = cursor.getColumnIndex(MEMO_COLUMN_CONTENT)
                val idxDate = cursor.getColumnIndex(MEMO_COLUMN_DATE)

                val categoryId = cursor.getInt(idxCategoryID)
                val title = cursor.getString(idxTitle)
                val content = cursor.getString(idxContent)
                val date = cursor.getString(idxDate)
                val memo = Memo(categoryId, title, content, date)
                memoList.add(memo)
            }
            cursor.close()
            db.close()
            return memoList
        }
    }
}