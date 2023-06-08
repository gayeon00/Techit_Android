package com.test.android14_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.test.android14_ex03.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val studentMutableList = mutableListOf<Student>()

        activityMainBinding.run {
            thread {
                SystemClock.sleep(1000)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }

            //포커스 주기
            editTextStudentName.requestFocus()
            //이후, 스레드 통해 키보드 올라옴

            //마지막 editText의 엔터 누르면
            editTextNumberMatScore.setOnEditorActionListener { mtextView, i, keyEvent ->
                //학생 정보 저장
                val student = Student(
                    editTextStudentName.text.toString(),
                    editTextNumberAge.text.toString().toInt(),
                    editTextNumberKorScore.text.toString().toInt(),
                    editTextNumberEngScore.text.toString().toInt(),
                    editTextNumberMatScore.text.toString().toInt()
                )
                studentMutableList.add(student)

                //editText다 비우기
                editTextStudentName.setText("")
                editTextNumberAge.setText("")
                editTextNumberKorScore.setText("")
                editTextNumberEngScore.setText("")
                editTextNumberMatScore.setText("")

//                //첫번재 editText에 포커스주기
                editTextStudentName.requestFocus()

                textView.text = "${studentMutableList.size}명"
                true
            }

            button.setOnClickListener {
                for (student in studentMutableList) {
                    Log.v("studentInfo", student.toString())
                }
                Log.v("studentInfo", totalScorePerSubject(studentMutableList))
                Log.v("studentInfo", avgScorePerSubject(studentMutableList))
            }

        }
    }

    private fun avgScorePerSubject(studentMutableList: MutableList<MainActivity.Student>): String {
        var korTotal = 0
        var engTotal = 0
        var matTotal = 0

        for (i in studentMutableList) {
            korTotal += i.korScore
            engTotal += i.engScore
            matTotal += i.matScore
        }

        return "국어 평균 : ${korTotal/studentMutableList.size}\n영어 평균 : ${engTotal/studentMutableList.size}\n수학 평균 : ${matTotal/studentMutableList.size}\n"

    }

    private fun totalScorePerSubject(studentMutableList: MutableList<Student>): String {
        var korTotal = 0
        var engTotal = 0
        var matTotal = 0

        for (i in studentMutableList) {
            korTotal += i.korScore
            engTotal += i.engScore
            matTotal += i.matScore
        }

        return "국어 총점 : $korTotal\n영어 총점 : $engTotal\n수학 총점 : $matTotal\n"
    }

    data class Student(
        val name: String,
        val age: Int,
        val korScore: Int,
        val engScore: Int,
        val matScore: Int
    ) {

    }
}