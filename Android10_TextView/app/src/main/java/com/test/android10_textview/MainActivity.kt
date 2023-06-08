package com.test.android10_textview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android10_textview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.run {

            textView2.run {
                //문자열 출력
                text = "안녕하세용"

                //배경 색상
//                setBackgroundColor(Color.RED)
                setBackgroundColor(Color.argb(10, 0, 255, 191))

                //글자색 설정
                setTextColor(Color.RED)

                //새로운 문자열 생성 -> 덮어쓰기
                text = "문자열1"

                //문자열 추가
                append("문자열2")
                append("문자열3")
            }


        }


    }
}