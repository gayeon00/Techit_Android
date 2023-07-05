package com.test.android78_sqliteex01.db

import android.content.Context
import com.test.android78_sqliteex01.Student

class DAO {
    companion object {
        fun insertData(context: Context, student: Student) {
            //autoIncrement가 있는 컬럼을 제외하고 나머지만 지정하면 됨
            val sql = """insert into StudentTable
                | (name, age, kor)
                | values (?, ?, ?)
            """.trimMargin()

            //?에 설정할 값을 배열에 담기
            val arg1 = arrayOf(
                student.name,
                student.age,
                student.korean,
            )

            //데이터베이스 오픈
            val sqliteDatabase = DBHelper(context)
            //쿼리 실행 (쿼리문, ?에 세팅할값 배열 arg1)
            sqliteDatabase.writableDatabase.execSQL(sql, arg1)
            //db닫기
            sqliteDatabase.close()
        }

        fun selectData(context: Context, idx: Int): Student {
            //쿼리문
            val sql = "select * from StudentTable where idx=?"
            //?에 들어갈 값 -> 배열로 받는 놈이라 하나만 있어도 배열로 만들어주기
            val arg1 = arrayOf(idx.toString())

            //데이터베이스 오픈
            val dbHelper = DBHelper(context)
            //쿼리 실행 -> 조건에 맞는 행 하나만 가져오는 거니까 한번만 하면 댐
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            //컬럼의 이름을 지정하여 컬럼의 순서값을 가져옴 (불편;;)
            val idxName = cursor.getColumnIndex("name")
            val idxAge = cursor.getColumnIndex("age")
            val idxKor = cursor.getColumnIndex("kor")

            val name = cursor.getString(idxName)
            val age = cursor.getInt(idxAge)
            val korean = cursor.getInt(idxKor)

            dbHelper.close()
            return Student(name, age, korean)
        }

        fun selectAllData(context: Context): MutableList<Student> {
            //모든 행을 가져오는쿼리문 작성
            val sql = "select * from StudentTable"

            //db 오픈
            val dbHelper = DBHelper(context)
            //쿼리실행 -> 얘는 반환값 받아오는 쿼리 실행!
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null);
            //cursor 객체에는 쿼리문에 맞는 행에 접근할 수 있는 객체가됨
            //처음에는 아무 행도 가르키고 있지 않음
            //moveToNext 메서드를호출하면 다음 행에 접근할 수 있음
            //이때 접근할 행이 있으면 true, 없으면 false 반환

            val dataList = mutableListOf<Student>()
            while (cursor.moveToNext()) {
                //컬럼의 이름을 지정하여 컬럼의 순서값을 가져옴 (불편;;)
                val idxName = cursor.getColumnIndex("name")
                val idxAge = cursor.getColumnIndex("age")
                val idxKor = cursor.getColumnIndex("kor")

                //데이터 가져옴
                val name = cursor.getString(idxName)
                val age = cursor.getInt(idxAge)
                val korean = cursor.getInt(idxKor)

                val student = Student(name, age, korean)
                dataList.add(student)
            }

            dbHelper.close()

            return dataList
        }
    }
}