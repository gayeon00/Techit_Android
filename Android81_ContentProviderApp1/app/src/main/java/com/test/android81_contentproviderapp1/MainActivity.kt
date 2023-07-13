package com.test.android81_contentproviderapp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android81_contentproviderapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            buttonSelectData.setOnClickListener {
                val sql = "select * from ${DBHelper.TABLE_NAME}"

                val dbHelper = DBHelper(this@MainActivity)
                val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

                textViewResult.text = ""

                while (cursor.moveToNext()) {
                    val idx1 = cursor.getColumnIndex("idx")
                    val idx2 = cursor.getColumnIndex("textData")
                    val idx3 = cursor.getColumnIndex("intData")
                    val idx4 = cursor.getColumnIndex("doubleData")
                    val idx5 = cursor.getColumnIndex("dateData")

                    val idx = cursor.getInt(idx1)
                    val textData = cursor.getString(idx2)
                    val intData = cursor.getInt(idx3)
                    val doubleData = cursor.getDouble(idx4)
                    val dateData = cursor.getString(idx5)

                    textViewResult.append("idx: $idx\n")
                    textViewResult.append("textData: $textData\n")
                    textViewResult.append("intData: $intData\n")
                    textViewResult.append("doubleData: $doubleData\n")
                    textViewResult.append("dateData: $dateData\n")
                }

                dbHelper.close()
            }
        }
    }
}