package com.test.android84_basicresource

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android84_basicresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                //textview의 문자열을 직접 설정
                //res/values 폴더에 있는 문자열을 가지고 온다
//                textView.text = resources.getText(R.string.str2)
                textView.setText(R.string.str2)
            }

            button2.setOnClickListener {
                //문자열을 가지고 온다.//문자열 리소스만 제공되는 아주 편리한 함수
                val str1 = getString(R.string.str3)
                //%부분을 채워서 완성
                val str2 = String.format(str1, "홍길동", 30)

                textView.text = str2
            }

            button3.setOnClickListener {
                //문자열 배열 가져오기
                val str4Array = resources.getStringArray(R.array.str4_array)
                textView.text = ""

                for (str1 in str4Array) {
                    textView.append("$str1\n")
                }
            }

            button4.setOnClickListener {
                //사전에 정의되어있는 색상값 사용
                textView.setTextColor(Color.BLUE)

                //RGB지정
                val c1 = Color.rgb(227,30,89)
                textView.setTextColor(c1)

                //ARGB 지정 (투명도)
                val c2 = Color.argb(50, 227, 30, 89)
                textView.setTextColor(c2)
            }
        }
    }
}