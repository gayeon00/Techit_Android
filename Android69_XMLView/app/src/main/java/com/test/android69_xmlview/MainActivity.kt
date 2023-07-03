package com.test.android69_xmlview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.test.android69_xmlview.databinding.ActivityMainBinding
import com.test.android69_xmlview.databinding.LayoutSub1Binding
import com.test.android69_xmlview.databinding.LayoutSub3Binding
import com.test.android69_xmlview.databinding.LayoutSub4Binding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var layoutSub3Binding: LayoutSub3Binding
    lateinit var layoutSub4Binding: LayoutSub4Binding

    lateinit var layoutSub1Binding: LayoutSub1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //layoutinlnflater로 뷰 생섳
        val sub1 = layoutInflater.inflate(R.layout.layout_sub1, null)
        val sub2 =layoutInflater.inflate(R.layout.layout_sub2, null)

        //뷰바인딩 샏ㅇ성
        layoutSub3Binding = LayoutSub3Binding.inflate(layoutInflater)
        layoutSub4Binding = LayoutSub4Binding.inflate(layoutInflater)

        //생성한 view들을 layout에 추가
        activityMainBinding.mainContainer.addView(sub1)
        activityMainBinding.mainContainer.addView(sub2)
        activityMainBinding.mainContainer.addView(layoutSub3Binding.root)
        activityMainBinding.mainContainer.addView(layoutSub4Binding.root)

//        //layout_sub1에 있는 버튼과 textview를 가져오기
//        val buttonSub1 = sub1.findViewById<Button>(R.id.buttonSub1)
//        val textViewSub1 = sub1.findViewById<TextView>(R.id.textViewSub1)
//
//        buttonSub1.setOnClickListener {
//            textViewSub1.text = "sub1 버튼 누름"
//        }
        //그냥 viewBinding으로 하자!!!!

        layoutSub1Binding = LayoutSub1Binding.bind(sub1)
        layoutSub1Binding.run {
            buttonSub1.setOnClickListener {
                textViewSub1.text = "sub1 버튼 누름"
            }
        }

        layoutSub3Binding.run {
            buttonSub3.setOnClickListener {
                textViewSub3.text = "sub3 버튼을 눌렀삼"
            }
        }
    }
}