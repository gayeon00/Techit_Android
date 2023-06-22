package com.test.android48_startactivityexport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android48_startactivityexport.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var activityThirdBinding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityThirdBinding.root)

        activityThirdBinding.run {
            val data1 = intent.getIntExtra("data1",0)
            val data2 = intent.getStringExtra("data2")

            textViewResult1.text = "data1: $data1\n"
            textView2.append("data2: $data2")

            button.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("value1", 200)
                resultIntent.putExtra("value2", "감사용")
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}