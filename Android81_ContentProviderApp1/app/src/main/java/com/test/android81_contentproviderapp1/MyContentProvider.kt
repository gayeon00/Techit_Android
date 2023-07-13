package com.test.android81_contentproviderapp1

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MyContentProvider : ContentProvider() {
    private var sqliteDatabase: SQLiteDatabase? = null


    // delete
    // 두 번째 : 조건절
    // 세 번째 : 조건절의 ?에 설정될 값 배열
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val cnt = sqliteDatabase?.delete(DBHelper.TABLE_NAME, selection, selectionArgs)
        //반환: 몇개 지웠는지
        return cnt!!
    }

    // 컬럼의 데이터 타입을 MIME 타입 형태로 문자열을 만들어 반환하는 메서드
    // 알려줄 필요가 없다면 null 반환
    // 컬럼 이름 알려주는 함수
    override fun getType(uri: Uri): String? {
        return null
    }

    //두 번째 매개변수 : 저장할 데이터의 컬럼 이름과 값이 담긴 객체
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        //저장한다.
        sqliteDatabase?.insert(DBHelper.TABLE_NAME, null, values)
        //db안닫아도됨!
        return uri
    }

    //Content Provider 객체가 생성되면 자동으로호출
    //DB에 접근할 수 있는 객체를 생성하고
    //접속에 성공하면 true, 실패하면 false를 반환하도록 구현한다.
    override fun onCreate(): Boolean {
        val dbHelper = DBHelper(context!!)
        //접속에 실패하면 null을 반환하고
        sqliteDatabase = dbHelper.writableDatabase
        //false를 반환하도록 한다.
        return sqliteDatabase != null
    }

    // select
    // 두 번째 : 가져올 컬럼 목록
    // 세 번째 : 조건절
    // 네 번째 : 조건절 ?에 설정될 값
    // 다 번째 : 정렬 기준
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor = sqliteDatabase?.query(DBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
        return cursor
    }

    // 두 번째 : 변경할 컬럼의 이름과 값이 있는 ContentValues 객체
    // 세 번째 : 조건절
    // 네 번째 : 조건절의 ? 에 설정될 값들의 배열
    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?
    ): Int {
        val cnt = sqliteDatabase?.update(DBHelper.TABLE_NAME, values, selection, selectionArgs)
        return cnt!!

    }
}