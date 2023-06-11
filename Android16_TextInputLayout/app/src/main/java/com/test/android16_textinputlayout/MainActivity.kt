package com.test.android16_textinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.test.android16_textinputlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            textInputLayout.run {
                editText?.run{
                    addTextChangedListener {
                        //editText의 error
                        error = if(it!=null && it.length > 10){
                            "10글자 이하로 입력해주세요"
                        } else{
                            null
                        }

//                        //textInputLayout의 error
//                        textInputLayout.error = if(it!=null && it.length > 10){
//                            "10글자 이하로 입력해주세요"
//                        } else{
//                            null
//                        }
                    }

                }
            }

            button.setOnClickListener {
//                //입력 내용 가져오기
//                val str1 = textInputEditText.text
//                textView.text = str1

                //textInputLayout으로부터 edittext추출
                if (textInputLayout.editText != null) {
                    val str2 = textInputLayout.editText!!.text.toString()
                    textView.text = str2

                }
            }
        }
    }
}