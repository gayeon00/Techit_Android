package com.test.android32_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import com.test.android32_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val mapList = mutableListOf<HashMap<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            listView.run{
                val keys = arrayOf("name", "age", "korScore")
                val ids = intArrayOf(R.id.textViewName, R.id.textViewAge, R.id.textViewKorScore)

                adapter = SimpleAdapter(
                    this@MainActivity, mapList, R.layout.row, keys, ids
                )
            }

            //마지막 editText에서 enter를 누르면
            editTextKorScore.setOnEditorActionListener { textView, i, keyEvent ->
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString()
                val korScore = editTextKorScore.text.toString()

                val map = hashMapOf<String, String>()
                map["name"] = name
                map["age"] = age
                map["korScore"] = korScore

                mapList.add(map)

                val adapter = listView.adapter as SimpleAdapter
                adapter.notifyDataSetChanged()

                //입력 내용 초기화
                editTextName.setText("")
                editTextAge.setText("")
                editTextKorScore.setText("")


                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                if(currentFocus!=null){
                    //키보드 내리기
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    //포커스 제거하기
                    currentFocus!!.clearFocus()
                }
                true
            }
        }
    }
}