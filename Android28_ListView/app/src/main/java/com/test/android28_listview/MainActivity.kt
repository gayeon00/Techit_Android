package com.test.android28_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.test.android28_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    // ListView를 구성하기 위해 필요한 데이터
    val data1 = arrayOf(
        "문자열1", "문자열2", "문자열3", "문자열4", "문자열5",
        "문자열6", "문자열7", "문자열8", "문자열9", "문자열10",
        "문자열11", "문자열12", "문자열13", "문자열14", "문자열15",
        "문자열16", "문자열17", "문자열18", "문자열19", "문자열20",
        "문자열21", "문자열22", "문자열23", "문자열24", "문자열25"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        // Adapter 생성
        // ArrayAdapter : 칸 하나에 문자열 하나만 사용하는 경우
        // android.R.layout.simple_list_item1 : 안드로이드에서
        // 리스트뷰의 항목 하나를 구성할 때 사용하라고 제공하는 layout
        // TextView 하나만 있다.

        // Context : 어떠한 작업을 위한 정보를 관리하는 요소들을 통칭한다.
        // 안드로이드는 시스템이나 애플리케이션과 관련된 정보를 가지고 있다.
        // Activity자체가 context를 상속받고 있어서 context넣을 자리에 activitycontext넣어주면 됨

        //Context, 항목 하나를 구성하기 위해 사용할 레이아웃, 텍스트 뷰에 채워줄 문자열 배열
        //지정된 레이아웃 안에 있는 android.R.id.text1이라는 View에 문자열을 세팅해줌
        val adapter = ArrayAdapter<String>(
            this,android.R.layout.simple_list_item_1, data1
        )

        //listview: 어떤 데이터를, 어떤 모양으로 몇개 보여줄 것인가?
        activityMainBinding.run {
            listView.run{
                //ListView에 어댑터 설정한다.
                setAdapter(adapter)

                //ListView의 항목 하나를 선택하면 동작하는 리스너
                //어떤 항목을 선택하는지 (position)가 중요함
                //position: 사용자가 터치한 항목의 순서값 0부터
                setOnItemClickListener { adapterView, view, i, l ->
                    textView.text = "${data1[i]}를 눌렀습니다."
                }
            }
        }


    }
}