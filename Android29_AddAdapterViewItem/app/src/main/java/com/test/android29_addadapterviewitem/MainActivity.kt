package com.test.android29_addadapterviewitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.test.android29_addadapterviewitem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val rowList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            //어댑터를 리스트로부터 추출
            //리스트뷰에 어댑터 설정
            listView.adapter = ArrayAdapter<String>(
                this@MainActivity, android.R.layout.simple_list_item_1, rowList
            )

            button.setOnClickListener {
                //Adapter를 구성하기 위해 사용한 list에 데이터를 추가하기
                //currentTimeMillis: 중복되지 않은 값이 필요할 때 많이 사용
                val str1 = "row : ${System.currentTimeMillis()}"
                rowList.add(str1)

                textView.text = "항목 개수 : ${rowList.size}"

                //ListView의 adapter을 통해 갱신 요청
                val adapter = listView.adapter as ArrayAdapter<String>
                adapter.notifyDataSetChanged()
            }

            button2.setOnClickListener {
                //제일 마지막 값 제거
                rowList.removeLast()

                textView.text = "항목 개수 : ${rowList.size}"

                val adapter = listView.adapter as ArrayAdapter<String>
                adapter.notifyDataSetChanged()
            }
        }
    }
}