package com.test.android21_compoundcomponent2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android21_compoundcomponent2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                textView.text = if (toggleButton.isChecked) {
                    "On 상태임다\n"
                } else {
                    "Off 상태임다\n"
                }

                if(switch1.isChecked){
                    textView.append("Switch: On상태임다\n")
                } else {
                    textView.append("Switch: Off상태임다\n")
                }
            }

            button2.setOnClickListener {
                toggleButton.isChecked = true
                switch1.isChecked = true
            }

            button3.setOnClickListener {
                toggleButton.isChecked = false
                switch1.isChecked = false
            }

            button4.setOnClickListener {
                toggleButton.toggle()
                switch1.toggle()
            }

            //on/off상태가 변경될 때 리스너
            toggleButton.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    textView2.text = "ToggleButton이 On 상태\n"
                } else {
                    textView2.text = "ToggleButton이 Off상태\n"
                }
            }

            /*[Switch]
            text : 좌측에 나타나는 문자열
            textOn : ON 상태일 때 표시할 문자열
            textOff : OFF 상태일 때 표시할 문자열
            showText : textOn, TextOff 에 설정할 문자열을 보여줄 것인가
            thumb : 버튼 이미지
            track : 트랙 이미지*/

            switch1.setOnCheckedChangeListener { compoundButton, b ->
                if(b) {
                    textView2.append("Switch가 On 상태\n")
                } else {
                    textView2.append("Switch가 off상태\n")
                }
            }
        }
    }
}