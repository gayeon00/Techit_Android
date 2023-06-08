package com.test.android02_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import com.test.android02_viewbinding.databinding.ActivityMainBinding
import com.test.android02_viewbinding.databinding.ActivityTestBinding

class MainActivity : AppCompatActivity() {

    //viewBinding
    //res/layout 폴더에 있는 xml 파일 하나당 하나의 클래스가 만들어진다.
    // 이 클래스에는 xml파일이 가지고 있는 View들을 관리하는 기능이 들어가있다.
    //이를 통하면 개발자가 View를 직접 추출하지않고 사용할수있다.
    //안드로이드 os가 알아서 view를 추출해서 변수에 담아준다.


    // 1. app 수준의 build.graldle 파일에 다음 코드를 추가해준다.
    // viewBinding {
    //     enabled = true
    // }


    //생성된 viewBinding 객체에는 view의 id와 동일한 이름의 변수가 만들어지고
    // 그 변수에는 view객체가 들어가있음
    // -> view에 id달아줘야함

    /*    lateinit var textView: TextView
        lateinit var button1: Button
        lateinit var button2: Button
        lateinit var button3: Button
        lateinit var button4: Button*/

    //viewBinding 객체를 담을변수
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var activityTestBinding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        //viewBinding 객체 가져오기
        //layoutInflater: xml파일들 가지고 눈에 보이는 요소 객체 만들어주는 놈
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        //xml파일 하나마다 만들 수 있음
        activityTestBinding = ActivityTestBinding.inflate(layoutInflater)

        //viewbinding객체가 관리하는 view 중에 최상위 뷰를 화면에 나타나게 함
        setContentView(activityMainBinding.root)
//        setContentView(activityTestBinding.root)

        /*        textView = findViewById(R.id.textView)
                button1 = findViewById(R.id.button1)
                button2 = findViewById(R.id.button2)
                button3 = findViewById(R.id.button3)
                button4 = findViewById(R.id.button4)*/

//        button1.setOnClickListener(Button1ClickListener())
//
//        button2.setOnClickListener(Button2ClickListener())
//
//        button3.setOnClickListener(Button3ClickListener())
//
//        button4.setOnClickListener(Button4ClickListener())

//        activityMainBinding.button1.setOnClickListener { Button1ClickListener() }
//        activityMainBinding.button2.setOnClickListener { Button2ClickListener() }
//        activityMainBinding.button3.setOnClickListener { Button3ClickListener() }
//        activityMainBinding.button4.setOnClickListener { Button4ClickListener() }

        //kotlin에서만 할 수 있는 것!
        //OnclickListener은 overriding할게 하나밖에 없기 때문에 객체 말고고차함수로 대체할 수 있음!!
        //overriding한 함수에서 수행할 동작을 람다식으로 고차함수 내에 넣어줌
        activityMainBinding.button1.setOnClickListener {
            activityMainBinding.textView.text = "20"
        }

        activityMainBinding.button2.setOnClickListener {
            activityMainBinding.textView.text = "0"
        }

        activityMainBinding.button3.setOnClickListener {
            activityMainBinding.textView.text = "100"
        }

        activityMainBinding.button4.setOnClickListener {
            activityMainBinding.textView.text = "1"
        }


        //run을 이용할 수도 있음 -> 편한걸로 하믄됨~
        activityMainBinding.run {
            button1.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "20"
                }
            }

            button2.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "0"
                }
            }

            button3.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "100"
                }
            }

            button4.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "1"
                }
            }
        }

    }


    inner class Button1ClickListener : OnClickListener {
        override fun onClick(p0: View?) {
//            textView.text = "20"
            activityMainBinding.textView.text = "20"
        }
    }

    inner class Button2ClickListener : OnClickListener {
        override fun onClick(p0: View?) {
//            textView.text = "0"
            activityMainBinding.textView.text = "0"
        }
    }

    inner class Button3ClickListener : OnClickListener {
        override fun onClick(p0: View?) {
//            textView.text = "100"
            activityMainBinding.textView.text = "100"
        }
    }

    inner class Button4ClickListener : OnClickListener {
        override fun onClick(p0: View?) {
//            textView.text = "1"
            activityMainBinding.textView.text = "1"
        }
    }
}