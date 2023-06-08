package com.test.android14_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.test.android14_ex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button5.setOnClickListener {
                val number = editTextText.text.toString().toInt()

                textView.text = ""
                for(i in 1..9){
                    textView.append("$number X $i = ${number*i}\n")
                }
            }
        }
    }
}