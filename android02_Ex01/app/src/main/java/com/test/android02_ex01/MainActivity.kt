package com.test.android02_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // 안드로이드 애플리케이션 동작 순서
    // 1. 사용자가 애플리케이션 아이콘을 터치하면
    // 2. AndroidManifest.xml에 기록되어 있는 Activity들 중
    // "android.intent.action.MAIN" 이름으로 설정되어 있는 intent filter가
    // 있는 Activity를 찾는다.
    // 3. 찾은 activity에 name 에 설정되어 있는 클래스 이름을 찾는다.
    // 4. 찾은 클래스의 객체를 생성하여 onCreate 메서드를 호출해준다.
    
    lateinit var textView: TextView
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Activity가 보여줄 화면을 설정하는 부분
        // R -> res 폴더
        // layout -> res 폴더 안에 있는 layout 폴더를 의미한다.
        // activity_main -> layout 폴더에 있는 xml 파일 이름
        setContentView(R.layout.activity_main)

        // setContentView에 설정된 화면에서
        // id가 button, textView인 요소들의 객체 ID값을 가져온다.
        textView = findViewById(R.id.textView)
        button1 = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener {
            textView.text = "10 + 10 = 20"
        }

        button2.setOnClickListener {
            val result = 10-10
            textView.text = "10 - 10 = $result"
        }
        button3.setOnClickListener {
            val result = 10*10
            textView.text = "10 * 10 = $result"
        }
        button4.setOnClickListener {
            val result = 10/10
            textView.text = "10 / 10 = $result"
        }
    }
}