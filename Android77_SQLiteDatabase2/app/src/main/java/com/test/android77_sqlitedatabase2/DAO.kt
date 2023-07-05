package com.test.android77_sqlitedatabase2

import android.content.ContentValues
import android.content.Context

class DAO {

    companion object {
        // CRUD 구현할 거임

        //CREATE
        fun insertData(context: Context, data: TestClass) {
            //컬럼 이름과 데이터를 설정하는 객체
            val contentValues = ContentValues()
            //어떤 컬럼에 무슨 값 집어넣는 지 설정
            contentValues.put("textData", data.data1)
            contentValues.put("intData", data.data2)
            contentValues.put("doubleData", data.data3)
            contentValues.put("dateDate", data.data4)

            //데이터베이스 오픈
            val sqliteDatabase = DBHelper(context)
            //쿼리 실행
            sqliteDatabase.writableDatabase.insert("TestTable", null, contentValues)
            //db닫기
            sqliteDatabase.close()
        }

        //Read Condition: 조건에 맞는 행 하나 가져옴
        fun selectData(context: Context, idx: Int): TestClass {

            //데이터베이스 오픈
            val dbHelper = DBHelper(context)
            // 첫 번째 : 테이블명
            // 두 번째 : 가지고 오고자 하는 컬럼 이름 목록. null을 넣어주면 모두 가져온다.
            // 세 번째 : 특정 행을 선택하기 위한 조건절
            // 네 번째 : 세 번째에 들어가는 조건절의 ? 에 셋팅될 값 배열
            // 다섯 번째 : Group by의 기준 컬럼
            // 여섯 번째 : Having절에 들어갈 조건절
            // 일곱 번째 : Having절의 ?에 셋팅될 값 배열
            val selection = "idx = ?"
            val args = arrayOf(idx.toString())
            val cursor = dbHelper.writableDatabase.query(
                "TestTable", null, selection, args, null, null, null
            )

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

            val dbHelper = DBHelper(context)
            // 첫 번째 : 테이블명
            // 두 번째 : 가지고 오고자 하는 컬럼 이름 목록. null을 넣어주면 모두 가져온다.
            // 세 번째 : 특정 행을 선택하기 위한 조건절
            // 네 번째 : 세 번째에 들어가는 조건절의 ? 에 셋팅될 값 배열
            // 다섯 번째 : Group by의 기준 컬럼
            // 여섯 번째 : Having절에 들어갈 조건절
            // 일곱 번째 : Having절의 ?에 셋팅될 값 배열
            val cursor =
                dbHelper.writableDatabase.query("TestTable", null, null, null, null, null, null)

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
            //컬럼 이름과 데이터를 설정하는 객체
            val contentValues = ContentValues()
            //어떤 컬럼에 무슨 값 집어넣는 지 설정
            contentValues.put("textData", obj.data1)
            contentValues.put("intData", obj.data2)
            contentValues.put("doubleData", obj.data3)
            contentValues.put("dateDate", obj.data4)

            //조건절
            val condition = "idx=?"
            //?에 들어갈 값
            val arg = arrayOf(obj.idx.toString())

            //수정하기
            //데이터베이스 열고
            val dbHelper = DBHelper(context)
            //쿼리 실행
            dbHelper.writableDatabase.update("TestTable", contentValues, condition, arg)
            dbHelper.close()
        }

        //Delete: 조건에 맞는 행 삭제
        fun deleteData(context: Context, idx: Int) {
            //조건절
            val condition = "idx=?"
            //?에 들어갈 값
            val arg = arrayOf(idx.toString())

            //쿼리실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.delete("TestTable", condition, arg)
            dbHelper.close()
        }
    }
}