package com.test.android51_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android51_ex01.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var activityResultBinding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityResultBinding.root)

        activityResultBinding.run {
            val position = intent.getIntExtra("position",0)
            textViewName.text = DataClass.humanList[position].name
            textViewDate.text = DataClass.humanList[position].date
            textViewGender.text = DataClass.humanList[position].gender
            textViewHobby.text = ""

            for(s1 in DataClass.humanList[position].hobbyList) {
                textViewHobby.append("${s1}\n")
            }

            buttonToMain.setOnClickListener {
                finish()
            }
        }
    }
}