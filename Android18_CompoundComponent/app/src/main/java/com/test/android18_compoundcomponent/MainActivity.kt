package com.test.android18_compoundcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android18_compoundcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run {
                setOnClickListener {
                    //체크박스 상태를 true로 설정
                    checkBox.isChecked = true
                    checkBox2.isChecked = true
                    checkBox3.isChecked = true

                    //라디오 그룹에서 하나를 선택
                    //매개변수로 선택할 라디오의 id설정
                    radioGroup1.check(R.id.radioButton3)
                    radioGroup2.check(R.id.radioButton6)
                }
            }

            button2.run {
                setOnClickListener {
                    //체크박스 상태를 false로 설정
                    checkBox.isChecked = false
                    checkBox2.isChecked = false
                    checkBox3.isChecked = false
                }
            }

            button3.run {
                setOnClickListener {
                    //체크박스 상태를 false로 설정
                    checkBox.toggle()
                    checkBox2.toggle()
                    checkBox3.toggle()
                }
            }

            button4.run {
                setOnClickListener {
                    textView.text = ""

                    if(checkBox.isChecked){
                        textView.append("첫 번째: 체크됨\n")
                    } else {
                        textView.append("첫 번째: 체크X\n")
                    }

                    if(checkBox2.isChecked){
                        textView.append("두 번째: 체크됨\n")
                    } else {
                        textView.append("두 번째: 체크X\n")
                    }

                    if(checkBox3.isChecked){
                        textView.append("세 번째: 체크됨\n")
                    } else {
                        textView.append("세 번째: 체크X\n")
                    }

                    //라디오 그룹을 통해 선택돼있는 버튼 id를 가져옴
                    when(radioGroup1.checkedRadioButtonId){
                        R.id.radioButton -> {
                            textView.append("라디오 1-1 선택\n")
                        }
                        R.id.radioButton2 -> {
                            textView.append("라디오 1-2 선택\n")
                        }
                        R.id.radioButton3 -> {
                            textView.append("라디오 1-3 선택\n")
                        }
                    }

                    when(radioGroup2.checkedRadioButtonId){
                        R.id.radioButton3 -> {
                            textView.append("라디오 2-1 선택\n")
                        }
                        R.id.radioButton4 -> {
                            textView.append("라디오 2-2 선택\n")
                        }
                        R.id.radioButton5 -> {
                            textView.append("라디오 2-3 선택\n")
                        }
                        R.id.radioButton6 -> {
                            textView.append("라디오 2-4 선택\n")
                        }
                    }
                }
            }
            //얘는 체크박스 하나마다
            checkBox.run {
                //체크박스의 선택상태가 변경됐을 때 동작
                //isChecked안에 선택상태에 대한 값이 전달된다.
                setOnCheckedChangeListener { compoundButton, isChecked ->
                    if(isChecked){
                        textView.text = "첫 번째: 체크됨"
                    } else {
                        textView.text = "첫 번째: 체크 해제"
                    }
                }
            }

            //얘는 라디오 그룹에
            radioGroup1.run {
                //라디오 그룹 내의 라디오 버튼 선택이 변경될 경우...
                //checkedId: 선택된 라디오 버튼의 ID
                setOnCheckedChangeListener { radioGroup, checkedId ->
                    when(checkedId) {
                        R.id.radioButton -> {
                            textView.text = "라디오 1-1 선택"
                        }
                        R.id.radioButton2 -> {
                            textView.text = "라디오 1-2 선택"
                        }
                        R.id.radioButton3 -> {
                            textView.text = "라디오 1-3 선택"
                        }
                    }
                }
            }
        }
    }
}