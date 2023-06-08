package com.test.android01_helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //안드로이드 어플리케이션 동작 순서

    //1. 사용자가 아이콘을 터치하면
    //2. AndroidManifest.xml에 기록돼있는 Activity들 중
    //   "android.intent.action.MAIN" 이름으로 설정되어 있는 intent filter가 있는 Activity를 찾는다.
    //3. 찾은 Activity에 name에 설정돼있는 클래스를 찾는다.
    //4. 찾은 클래스의 객체를 생성하여 onCreate 메서드를 호출해준다.

    lateinit var button: Button
    lateinit var button2: Button
    lateinit var textView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Activity가 보여줄 화면을 설정하는 부분
        //R -> res폴더
        //layout -> res/layout폴더
        //activity_main -> res/layout/activity_main.xml
        setContentView(R.layout.activity_main)


        //setContentView가 설정된 화면에서
        //id가 button, textView인 요소들의 객체 ID값을 가져온다.
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)

        button2 = findViewById(R.id.button2)

        //버튼을 눌렀을 때 동작할 메서드를 가지고 있는 객체를 생성해 실행한다.
        val buttonClickListener = ButtonClickListener()
        button.setOnClickListener(buttonClickListener)
        button2.setOnClickListener(buttonClickListener)

    }

    //버튼을 누르면 동작할 리스너
    inner class ButtonClickListener : OnClickListener {
        override fun onClick(p0: View?) {
            textView.text = "버튼을 눌렀습니당"
        }
    }
}