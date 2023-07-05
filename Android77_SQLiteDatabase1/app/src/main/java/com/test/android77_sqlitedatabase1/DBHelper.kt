package com.test.android77_sqlitedatabase1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Test.db: 사용할 db 파일의 이름
// null: NullFactory - null에 대한 처리를 어케 할 것인가
// 1: 버전
class DBHelper(context: Context) : SQLiteOpenHelper(context, "Test.db", null, 1) {
    //db 파일이 없으면 만들고 이 메서드를 호출함
    //테이블을 생성하는 작업 수행하면 됨
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        // 테이블의 구조 정의

        // create table 테이블이름
        // (컬럼이름 자료형 제약조건 ... )
        // 자료형 : 정수 - integer, 문자열 - text, 실수 - real, 날짜 - date
        // 제약조건 : 저장할 수 있는 값에 대한 조건
        // primary key : null을 허용하지 않고 중복된 값을 허용하지 않는다.
        // 각 행들을 개별적으로 구분할 수 있는 값을 저장하기 위해 사용한다.
        // autoincrement : 컬럼에 저장할 값을 지정하지 않으면 1부터 1씩 증가되는 값이 자동으로 저장한다.
        // not null : null 값을 허용하지 않는다. 즉, 개발자가 무조건 값을 정해줘야 한다.

        val sql="""create table TestTable
            (idx integer primary key autoincrement,
            textData text not null,
            intData integer not null,
            doubleData real not null,
            dateDate date not null)
        """.trimIndent()

        //쿼리문 수행
        sqLiteDatabase?.execSQL(sql)
    }

    // 사용하는 데이터베이스 파일의 버전이 변경되어 있을 때 호출되는 메서드
    // 부모의 생성자의 마지막에 넣어준 버전 번호가 데이터 베이스파일에 기록된 버전보다 높을 때호출
    // 과거에 만들어진 테이블을 현재의 구조가 될 수 있도록 테이블을 수정하는 작업을하면된다.
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}