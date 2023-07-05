package com.test.android77_sqlitedatabase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android77_sqlitedatabase1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            //insert
            button.setOnClickListener {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                //현재시간 갖고있는 데이터
                val now = sdf.format(Date())

                // 저장할 정보를 가지고 있는 객체를 생성한다.
                val obj1 = TestClass(0, "문자열1", 100, 11.11, now)
                DAO.insertData(this@MainActivity, obj1)

                val obj2 = TestClass(0, "문자열2", 200, 22.22, now)
                DAO.insertData(this@MainActivity, obj2)
            }

            button2.setOnClickListener {
                //데이터가져옴
                val dataList = DAO.selectAllData(this@MainActivity)

                //출력
                textView.text = ""

                for (obj in dataList) {
                    textView.append("idx : ${obj.idx}\n")
                    textView.append("textData : ${obj.data1}\n")
                    textView.append("intData : ${obj.data2}\n")
                    textView.append("doubleData : ${obj.data3}\n")
                    textView.append("dateData : ${obj.data4}\n\n")
                }
            }

            button3.setOnClickListener {
                val obj = DAO.seletData(this@MainActivity, 1)

                textView.text = "idx : ${obj.idx}\n"
                textView.append("textData : ${obj.data1}\n")
                textView.append("intData : ${obj.data2}\n")
                textView.append("doubleData : ${obj.data3}\n")
                textView.append("dateData : ${obj.data4}\n\n")
            }

            button4.setOnClickListener {
                val obj = DAO.seletData(this@MainActivity, 1)
                obj.data1 = "새 문자열"

                DAO.updateData(this@MainActivity, obj)
            }

            button5.setOnClickListener {
                DAO.deleteData(this@MainActivity, 1)
            }
        }


    }
}

data class TestClass(
    var idx: Int,
    var data1: String,
    var data2: Int,
    var data3: Double,
    var data4: String
)