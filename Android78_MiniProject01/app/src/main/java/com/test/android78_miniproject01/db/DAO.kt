package com.test.android78_miniproject01.db

import android.content.Context
import com.test.android78_miniproject01.Memo

//val sql="""create table MemoTable
//            (idx integer primary key autoincrement,
//            title text not null,
//            content integer not null,
//            date date not null)
class DAO {
    companion object {
        fun insertData(context: Context, memo: Memo) {
            //autoIncrement가 있는 컬럼을 제외하고 나머지만 지정하면 됨
            val sql = """insert into MemoTable
                | (title, content, date)
                | values (?, ?, ?)
            """.trimMargin()

            //?에 설정할 값을 배열에 담기
            val arg1 = arrayOf(
                memo.title,
                memo.content,
                memo.date
            )
            //데이터베이스 오픈
            val sqliteDatabase = DBHelper(context)
            //쿼리 실행 (쿼리문, ?에 세팅할값 배열 arg1)
            sqliteDatabase.writableDatabase.execSQL(sql, arg1)
            //db닫기
            sqliteDatabase.close()
        }

        fun selectAllData(context: Context): MutableList<Memo> {
            //모든 행을 가져오는쿼리문 작성
            val sql = "select * from MemoTable"

            //db 오픈
            val dbHelper = DBHelper(context)
            //쿼리실행 -> 얘는 반환값 받아오는 쿼리 실행!
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            //cursor 객체에는 쿼리문에 맞는 행에 접근할 수 있는 객체가됨
            //처음에는 아무 행도 가르키고 있지 않음
            //moveToNext 메서드를호출하면 다음 행에 접근할 수 있음
            //이때 접근할 행이 있으면 true, 없으면 false 반환

            val dataList = mutableListOf<Memo>()
            while (cursor.moveToNext()) {
                //컬럼의 이름을 지정하여 컬럼의 순서값을 가져옴 (불편;;)
                val idxTitle = cursor.getColumnIndex("title")
                val idxContent = cursor.getColumnIndex("content")
                val idxDate = cursor.getColumnIndex("date")

                //데이터 가져옴
                val title = cursor.getString(idxTitle)
                val content = cursor.getString(idxContent)
                val date = cursor.getString(idxDate)

                val student = Memo(title, content, date)
                dataList.add(student)
            }

            dbHelper.close()

            return dataList
        }

        fun deleteData(context: Context, memo: Memo) {
            //쿼리문
            val sql = "delete from MemoTable where date=?"
            //?에 들어갈 값
            val arg = arrayOf(memo.date)

            //쿼리실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, arg)
            dbHelper.close()
        }

        fun updateData(context: Context, oldMemo: Memo, newMemo: Memo) {
            //쿼리문
            //idx가 ? 인 textData, intData, doubleData, dateDate를 변경한다
            val sql = """update MemoTable
                |  set title=?, content=?, date=?
                |  where date=?
            """.trimMargin()

            val args = arrayOf(
                newMemo.title, newMemo.content, newMemo.date, oldMemo.date
            )

            //데이터베이스 열고
            val dbHelper = DBHelper(context)
            //쿼리 실행
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

    }
}