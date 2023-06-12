package com.test.android23_chip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android23_chip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                if(chip2.isChecked) {
                    textView.text = "선택 되어 있습니다.\n"
                } else {
                    textView.text = "선택 되어 있지 않습니다.\n"
                }

                when(ChipGroup.checkedChipId){
                    R.id.chip11 -> {
                        textView.append("라디오1 선택\n")
                    }
                    R.id.chip12 -> {
                        textView.append("라디오2 선택\n")
                    }
                    R.id.chip13 -> {
                        textView.append("라디오3 선택\n")
                    }
                }
            }
        }

        /*[Chip]
        체크박스, 라디오와 같은 동작 할수 있음

        style : chip의 모양을 설정한다. 생략시 Action, Choice, Entry, Filter
        Checkable : 체크가 가능한가.
        Text : 표시할 문자열
        chipicon : chip에 표시할 아이콘
        chipIconVisible : chip 아이콘을 표시할 것인가
        checkedIcon : 선택되었을 때의 아이콘
        checkedIconVisible : 선택되었을 때의 아이콘을 표시할 것인*/
    }
}