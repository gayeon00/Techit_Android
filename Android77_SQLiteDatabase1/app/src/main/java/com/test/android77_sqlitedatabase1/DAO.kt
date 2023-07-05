package com.test.android77_sqlitedatabase1

import android.content.Context

class DAO {

    companion object {
        // CRUD 구현할 거임

        //CREATE
        fun insertData(context: Context, data: TestClass) {
            //autoIncrement가 있는 컬럼을 제외하고 나머지만 지정하면 됨
            val sql = """insert into TestTable
                | (textData, intData, doubleData, dateDate)
                | values (?, ?, ?, ?)
            """.trimMargin()

            //?에 설정할 값을 배열에 담기
            val arg1 = arrayOf(
                data.data1,
                data.data2,
                data.data3,
                data.data4
            )

            //데이터베이스 오픈
            val sqliteDatabase = DBHelper(context)
            //쿼리 실행 (쿼리문, ?에 세팅할값 배열 arg1)
            sqliteDatabase.writableDatabase.execSQL(sql, arg1)
            //db닫기
            sqliteDatabase.close()

        }

        //Read Condition: 조건에 맞는 행 하나 가져옴
        fun seletData(context: Context, idx: Int): TestClass {
            //쿼리문
            val sql = "select * from TestTable where idx=?"
            //?에 들어갈 값 -> 배열로 받는 놈이라 하나만 있어도 배열로 만들어주기
            val arg1 = arrayOf(idx.toString())

            //데이터베이스 오픈
            val dbHelper = DBHelper(context)
            //쿼리 실행 -> 조건에 맞는 행 하나만 가져오는 거니까 한번만 하면 댐
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            //컬럼의 이름을 지정하여 컬럼의 순서값을 가져옴 (불편;;)
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("textData")
            val idx3 = cursor.getColumnIndex("intData")
            val idx4 = cursor.getColumnIndex("doubleData")
            val idx5 = cursor.getColumnIndex("dateDate")

            //데이터 가져옴
            val idx = cursor.getInt(idx1)
            val textData = cursor.getString(idx2)
            val intData = cursor.getInt(idx3)
            val doubleData = cursor.getDouble(idx4)
            val dateData = cursor.getString(idx5)

            dbHelper.close()
            return TestClass(idx, textData, intData, doubleData, dateData)
        }

        //Read All: 모든 행을 가져옴
        fun selectAllData(context: Context): MutableList<TestClass> {
            //모든 행을 가져오는쿼리문 작성
            val sql = "select * from TestTable"

            //db 오픈
            val dbHelper = DBHelper(context)
            //쿼리실행 -> 얘는 반환값 받아오는 쿼리 실행!
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null);
            //cursor 객체에는 쿼리문에 맞는 행에 접근할 수 있는 객체가됨
            //처음에는 아무 행도 가르키고 있지 않음
            //moveToNext 메서드를호출하면 다음 행에 접근할 수 있음
            //이때 접근할 행이 있으면 true, 없으면 false 반환

            val dataList = mutableListOf<TestClass>()
            while (cursor.moveToNext()) {
                //컬럼의 이름을 지정하여 컬럼의 순서값을 가져옴 (불편;;)
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("textData")
                val idx3 = cursor.getColumnIndex("intData")
                val idx4 = cursor.getColumnIndex("doubleData")
                val idx5 = cursor.getColumnIndex("dateDate")

                //데이터 가져옴
                val idx = cursor.getInt(idx1)
                val textData = cursor.getString(idx2)
                val intData = cursor.getInt(idx3)
                val doubleData = cursor.getDouble(idx4)
                val dateData = cursor.getString(idx5)

                val testClass = TestClass(idx, textData, intData, doubleData, dateData)
                dataList.add(testClass)
            }

            dbHelper.close()

            return dataList
        }

        //Update: 조건에 맞는 행의 컬럼 값을 수정
        fun updateData(context: Context, obj: TestClass) {
            //쿼리문
            //idx가 ? 인 textData, intData, doubleData, dateDate를 변경한다
            val sql = """update TestTable
                |  set textData=?, intData=?, doubleData=?, dateDate=?
                |  where idx=?
            """.trimMargin()

            val args = arrayOf(
                obj.data1, obj.data2, obj.data3, obj.data4, obj.idx
            )

            //데이터베이스 열고
            val dbHelper = DBHelper(context)
            //쿼리 실행
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()


        }

        //Delete: 조건에 맞는 행 삭제
        fun deleteData(context: Context, idx: Int) {
            //쿼리문
            val sql = "delete from TestTable where idx=?"
            //?에 들어갈 값
            val arg = arrayOf(idx)

            //쿼리실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, arg)
            dbHelper.close()
        }
    }
}