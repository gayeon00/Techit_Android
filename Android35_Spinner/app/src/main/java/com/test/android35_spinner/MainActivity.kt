package com.test.android35_spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.test.android35_spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val dataList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5",
        "항목6", "항목7", "항목8", "항목9", "항목10",
        "항목11", "항목12", "항목13", "항목14", "항목15",
        "항목16", "항목17", "항목18", "항목19", "항목20",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            spinner.run {
                val a1  = ArrayAdapter<String>(
                    //Spinner가 접혀있을 때의 모양
                    this@MainActivity, android.R.layout.simple_spinner_item, dataList
                )
                //Spinner가 펼쳤을때의 모양
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                //spinne의 adapter에 붙여주기
                adapter = a1

                //Spinner의 항목을 코드로 선택 position: 0부터
                setSelection(2)

                //항목을 선택하면 동작
                onItemSelectedListener = object :OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        textView.text = "${dataList[p2]} 항목을 선택했슴다."
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
            }

            button.setOnClickListener {
                //Spinner에서 선택된 항목의 순서값을 가져온다
                val position = spinner.selectedItemPosition
                textView2.text = "선택한 항목 : ${dataList[position]}"
            }


        }
    }
}