package com.test.android31_simpleadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import com.test.android31_simpleadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val imgData = arrayOf(
        R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3,
        R.drawable.imgflag4, R.drawable.imgflag5, R.drawable.imgflag6,
        R.drawable.imgflag7, R.drawable.imgflag8
    )

    // textViewRow1에 설정한 문자열 목록
    val textData1 = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    )

    // textViewRow2에 설정한 문자열 목록
    val textData2 = arrayOf(
        "togo", "france", "swiss", "spain", "japan", "german", "brazil",
        "korea"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            listViewMain.run {
                //simpleAdapter에 담을 데이터 구성
                //Hash맵 하나 당 항목 하나
                val dataList = ArrayList<HashMap<String, Any>>()

                for (idx in imgData.indices) {
                    //항목 하나를 구성하기 위해 필요한 데이터를 담을 map 생성
                    val map = HashMap<String, Any>()
                    map["img"] = imgData[idx]
                    map["data1"] = textData1[idx]
                    map["data2"] = textData2[idx]

                    //ArrayList에 담기
                    dataList.add(map)
                }

                //keys와 ids는 1대1매칭
                //hashmap에 데이터를 넣을 때 사용한 이름을 가지고 있는 배열
                val keys = arrayOf("img", "data1", "data2")
                //데이터를 셋팅할 View의 ID배열
                val ids = intArrayOf(R.id.imageViewRow, R.id.textViewRow1, R.id.textViewRow2)

                // listView의 항목 하나를 정의하는 원리
                // 1. SimpleAdapter에 설정하는 ArrayList 내의 HashMap의 개수 만큼 항목이 만들어진다.
                // 2. 각 항목을 개별적으로 구성하여 보여준다.
                // 3. 항목 하나를 구성하기 위해 항목 번째의 HashMap을 추출한다.
                // 4. HashMap에 있는 데이터를 keys 배열에 들어있는 이름의 순서대로 추출한다.
                // 5. ids 배열에 설정되어 있는 View의 ID 순서대로 데이터를 설정한다.

                //SimpleAdapter생성
                //dataList에서 i번째 hashmap추출, 여기서 keys의 i번째이름의 항목을 ids의 i번째 view에 넣어서 R.layout.row를 구성해줌
                adapter = SimpleAdapter(this@MainActivity, dataList, R.layout.row, keys, ids)
            }
        }
    }
}