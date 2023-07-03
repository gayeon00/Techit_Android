package com.test.android70_codeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.test.android70_codeview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    //생성된 뷰들을 담을 리스트
    val viewList = mutableListOf<EditText>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                //view의 가로세로길이
                val params = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                //editText생성
                val newEditText = EditText(this@MainActivity)
                newEditText.layoutParams = params
                newEditText.hint = "문자열 입력해주쇼"

                viewList.add(newEditText)

                //생성한 view를 추ㅏㄱ
                activityMainBinding.mainContainer.addView(newEditText)
            }

            button2.setOnClickListener {
                textView.text = ""
                //리스트에 들어있는 뷰의 수만큼 반복
                for (v in viewList) {
                    textView.append("${v.text.toString()}\n")
                }
            }

            button6.setOnClickListener {
                //순서를 지정해서 제거
//                activityMainBinding.mainContainer.removeViewAt(viewList.size - 1)
//                viewList.removeLast()

                //view지정해서 제거
                val lastView = viewList.last()
                activityMainBinding.mainContainer.removeView(lastView)
                viewList.removeLast()
            }

            //모든 뷰 지우기
            button7.setOnClickListener {
                activityMainBinding.mainContainer.removeAllViews()
            }
        }
    }
}