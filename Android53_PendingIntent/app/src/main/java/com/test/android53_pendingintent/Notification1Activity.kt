package com.test.android53_pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android53_pendingintent.databinding.ActivityNotification1Binding

class Notification1Activity : AppCompatActivity() {
    lateinit var activityNotification1Binding: ActivityNotification1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityNotification1Binding = ActivityNotification1Binding.inflate(layoutInflater)
        setContentView(activityNotification1Binding.root)

        val data1 = intent.getIntExtra("data1", 0)
        val data2 = intent.getStringExtra("data2")

        activityNotification1Binding.run {
            textViewNo1.text = "data1: ${data1}\n"
            textViewNo1.append("data2: $data2")
        }
    }
}