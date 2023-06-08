package com.test.android12_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import com.test.android12_edittext.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            //포커스가 있는 뷰의 키보드 올라오게 하기
            //onCreate메서드가 끝나야 화면이 나타남 -> 그 전에 키보드가 올라오게 하는
            // 명령을 전달하기 때문에 무시됨! => 스레드로 운영해야함!
            thread {
                //500ms 쉬게함
                //onCreate 수행이 끝날 때 까지 대기!
                SystemClock.sleep(1000)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus,0)
            }

            editTextText.run {
                //nullable이 들어와야 하는데 non-null이 들어와서 에러남!
                // -> 메서드 쓰믄 됨!
//            text = "블라블라"
                setText("코드에서 문자열 설정")

                //문자열 뒤에 포커스 주기
                requestFocus()

                //엔터키를 눌렀을 때의 이벤트
                setOnEditorActionListener { textView, i, keyEvent ->
                    textView.text = "엔터 버튼을 눌렀슴다."
                    textView2.text = text.toString()
                    //true를 반환하면 포커스가 유지
                    //false를 반환하면 포커스가 다음 editText로 이동
                    false
                }

                //입력 감시
                addTextChangedListener(EditTextWatcher1())
            }

            editTextTextMultiLine.run {
            //고차함수 사용 가능 -> afterchanged역할만 수행!
            //실시간으로 사용자 입력 받아올 수 있음
                addTextChangedListener {
                    textView.text = it
                }
            }

            button.run {
                setOnClickListener {
                    //EditText의 문자열(String타입으로) 가져오기
                    val str1 = editTextText.text.toString()
                    textView.text = str1

                    //키보드 내리기
                    //INPUT_METHOD_SERVICE -> 안드로이드 os가 관리하는 백그라운드 프로세스들 중 키보드 관련된 걸로 가져와주라
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    if (currentFocus != null) {
                        //currentFocus: 현재 포커스를 가지고 있는 View를 지칭할 수 있다.
                        //flag는 신경쓰지 말고 0으로
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

                        //포커스 해제하기
                        currentFocus!!.clearFocus()
                    }




                }
            }
        }
    }

    //EditText입력 감시자
    inner class EditTextWatcher1: TextWatcher{
        //입력 내용 변경 전
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            activityMainBinding.textView.text = "beford: ${p0}"
        }

        //변경 했을 때
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            activityMainBinding.textView2.text = "changed: $p0"
        }

        //변경 후
        override fun afterTextChanged(p0: Editable?) {
            activityMainBinding.textView3.text = "after: $p0"
        }
    }
}