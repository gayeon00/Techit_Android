package com.test.android45_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android45_ex01.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var activityAddBinding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityAddBinding.root)

        activityAddBinding.run {
            editTextData2.setOnEditorActionListener { textView, i, keyEvent ->
                val data1 = editTextData1.text.toString()
                val data2 = editTextData2.text.toString()

                val resultIntent = Intent()
                resultIntent.putExtra("data1", data1)
                resultIntent.putExtra("data2", data2)

                setResult(RESULT_OK, resultIntent)
                finish()

                false

            }
        }
    }
}