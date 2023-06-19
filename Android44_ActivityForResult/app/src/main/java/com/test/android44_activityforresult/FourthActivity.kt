package com.test.android44_activityforresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android44_activityforresult.databinding.ActivityFourthBinding

class FourthActivity : AppCompatActivity() {
    lateinit var activityFourthBinding: ActivityFourthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityFourthBinding = ActivityFourthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityFourthBinding.root)

        activityFourthBinding.run {
            val data1 = intent.getIntExtra("data1",0)
            val data2 = intent.getFloatExtra("data2", 0f)

            textViewFourth.run {
                text = "data1: $data1\n"
                append("data2: $data2\n")
            }

            buttonFourth.setOnClickListener {
                val resultIntent = Intent()
                //값 설정
                resultIntent.putExtra("value1",200)
                resultIntent.putExtra("value2",22.22)

                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}