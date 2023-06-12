package com.test.android22_checkedtextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android22_checkedtextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            //체크박스1
            checkedTextView.run {
                setOnClickListener {
                    //현재 체크 상태를 반전시키기
                    toggle()
                }
            }

            //체크박스1
            checkedTextView2.run {
                setOnClickListener {
                    toggle()
                }
            }

            //라디오1
            checkedTextView3.isChecked = true

            //그냥 textView정도 이기 때문에 radioGroup이 하는 일은 직접 구현해줘야함
            checkedTextView3.run {
                setOnClickListener {
                    isChecked = true
                    checkedTextView4.isChecked = false
                    checkedTextView5.isChecked = false
                }
            }

            //라디오2
            checkedTextView4.run {
                setOnClickListener {
                    isChecked = true
                    checkedTextView3.isChecked = false
                    checkedTextView5.isChecked = false
                }
            }

            //라디오3
            checkedTextView5.run {
                setOnClickListener {
                    isChecked = true
                    checkedTextView4.isChecked = false
                    checkedTextView3.isChecked = false
                }
            }

            button.setOnClickListener {
                checkedTextView.isChecked = true
                checkedTextView2.isChecked = true
            }

            button2.setOnClickListener {
                checkedTextView.isChecked = false
                checkedTextView2.isChecked = false
            }

            button3.setOnClickListener {
                textView.text = ""

                if (checkedTextView.isChecked) {
                    textView.append("CheckBox = true\n")
                } else {
                    textView.append("CheckBox = false\n")
                }

                if (checkedTextView2.isChecked) {
                    textView.append("CheckBox2 = true\n")
                } else {
                    textView.append("CheckBox2 = false\n")
                }

                if (checkedTextView3.isChecked) {
                    textView.append("Radio1 = true\n")
                } else if (checkedTextView4.isChecked) {
                    textView.append("Radio2 = true\n")
                } else if (checkedTextView5.isChecked) {
                    textView.append("Radio3 = true\n")
                }
            }
        }
    }
}