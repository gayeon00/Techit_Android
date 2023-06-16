package com.test.android43_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android43_startactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var activitySecondBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.run {
            buttonSecond.setOnClickListener {
                //현재 액티비티를 종료하고 백스택에서 제거
                finish()
            }
        }
    }
}