package com.test.android50_dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.test.android50_dialog.databinding.ActivityMainBinding
import com.test.android50_dialog.databinding.DialogBinding
import java.util.Calendar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val dateList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5", "항목6",
        "항목7", "항목8", "항목9", "항목10", "항목11", "항목12",
        "항목13", "항목14", "항목15", "항목16", "항목17", "항목18"
    )
    val multiChoiceList = BooleanArray(dateList.size) { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //다이얼로그- 사용자가 반드시 봐야하는 메세지
        activityMainBinding.run {
            button.setOnClickListener {
                //다이얼로그 생성을 위한 객체 생성
                val builder = AlertDialog.Builder(this@MainActivity)
                //타이틀
                builder.setTitle("ㅣㄱ본 다이얼로그")
                //메시지
                builder.setMessage("기본 다이얼로그임!")
                //아이콘
                builder.setIcon(R.mipmap.ic_launcher)

                //버튼 - listener에 null 해놓으면 버튼누르면 그냥 다이얼로그 사라짐
//                builder.setPositiveButton("Positive", null)
//                builder.setNegativeButton("Negative",null)
//                builder.setNeutralButton("Neutral", null)

                builder.setPositiveButton("Positive") { dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Positive 버튼 누름"
                }
                builder.setNegativeButton("Negative") { dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Negative 버튼 누름"
                }
                builder.setNeutralButton("Neutral") { dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Neutral 버튼 누름"
                }

                //다이얼로그를 띄움
                builder.show()
            }

            button2.setOnClickListener {
                val dialogBinding = DialogBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("커스텀 다이얼로그")
                builder.setIcon(R.mipmap.ic_launcher)

                //새로운 뷰를 설정한다
                builder.setView(dialogBinding.root)

                dialogBinding.editTextDialog1.requestFocus()
                thread {
                    SystemClock.sleep(1000)
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(dialogBinding.editTextDialog1, 0)
                }

                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                    //입력한 내용 가져옴
                    val str1 = dialogBinding.editTextDialog1.text.toString()
                    val str2 = dialogBinding.editTextDialog2.text.toString()

                    textView.text = "$str1\n"
                    textView.append(str2)

                }
                builder.setNegativeButton("취소", null)

                builder.show()
            }

            button3.setOnClickListener {
                //날짜를 선택하기 위해 사용하는 다이얼로그
                val calendar = Calendar.getInstance()
                //현재날짜
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DATE)

                //datepicker 다이얼로그 생성 (현재날짜로 생성)
                val pickerDialog = DatePickerDialog(
                    this@MainActivity,
                    //날짜 선택하면 동작할 리스너 (분리해도됨)
                    //i~i3 : 년,월,일
                    DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                        textView.text = "${i}년 ${i2 + 1}월 ${i3}일"
                    },
                    year,
                    month,
                    day
                )
                pickerDialog.show()
            }

            button4.setOnClickListener {
                val calendar = Calendar.getInstance()

                val hour = calendar.get(Calendar.HOUR)
                val minute = calendar.get(Calendar.MINUTE)

                val pickerDialog = TimePickerDialog(
                    this@MainActivity,
                    TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                        textView.text = "${i}시 ${i2}분"
                    },
                    hour,
                    minute,
                    true
                )

                pickerDialog.show()
            }

            button5.setOnClickListener {
                val adapter = ArrayAdapter<String>(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    dateList
                )

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("리스트 다이얼로그")
                builder.setIcon(R.mipmap.ic_launcher)
                //어댑터 설정
                builder.setAdapter(adapter) { dialogInterface: DialogInterface, i: Int ->
                    //i:선택한 항목의 순서값
                    textView.text = "리스트 ${i}번째 선택 "
                }
                builder.setNegativeButton("취소", null)
                builder.show()
            }

            button6.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("다중 선택 리스트 다이얼로그")

                val checkArray = BooleanArray(18) { false }
                builder.setMultiChoiceItems(dateList, checkArray, null)

                builder.setNegativeButton("취소", null)
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                    textView.text = ""

                    //다이얼로그에서 listview추출
                    val alertDialog = dialogInterface as AlertDialog
                    //체크 상태가 변경된 아이들, true인 아이들 섞여서 나옴
                    val position = alertDialog.listView.checkedItemPositions

                    for (idx in 0 until position.size()) {
                        //현재 항목의 위차값
                        val pos1 = position.keyAt(idx)
                        checkArray[pos1] = position[pos1]
                    }

                    for (idx in checkArray.indices) {
                        if (checkArray[idx]) {
                            textView.append("${dateList[idx]}\n")
                        }
                    }
                }
                builder.show()
            }

            button7.setOnClickListener {
                //강사님의 방식
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("다중 선택 리스트 다이얼로그")

                //마지막 매개변수에 체크 상태가 변경되었을 때 마다 동작하는 리스너를 설정
                //여기에서 변경된 체크 상태값을 BooleanArray에 담아둠
                //i: 변경된 체크 항목의 순서값
                //b: 체크상태
                builder.setMultiChoiceItems(
                    dateList,
                    multiChoiceList
                ) { dialogInterface: DialogInterface, i: Int, b: Boolean ->
                    multiChoiceList[i] = b
                }

                builder.setNegativeButton("취소", null)
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    for(idx in multiChoiceList.indices) {
                        if (multiChoiceList[idx]) {
                            textView.append("${dateList[idx]}\n")
                        }
                    }

                }

                builder.show()
            }
        }
    }
}