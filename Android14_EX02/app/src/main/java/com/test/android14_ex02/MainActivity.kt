package com.test.android14_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android14_ex02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            //더하기
            button.setOnClickListener {
                textView.text = (editTextNumber.text.toString().toInt() + editTextNumber2.text.toString().toInt()).toString()
            }
            //빼기
            button2.setOnClickListener {
                textView.text = (editTextNumber.text.toString().toInt() - editTextNumber2.text.toString().toInt()).toString()
            }
            //곱하기
            button3.setOnClickListener {
                textView.text = (editTextNumber.text.toString().toInt() * editTextNumber2.text.toString().toInt()).toString()
            }
            //나누기
            button4.setOnClickListener {
                textView.text = (editTextNumber.text.toString().toInt() / editTextNumber2.text.toString().toInt()).toString()
            }
        }
    }
}