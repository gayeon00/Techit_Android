package com.test.android53_pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android53_pendingintent.databinding.ActivityNotification2Binding

class Notification2Activity : AppCompatActivity() {
    lateinit var activityNotification2Binding: ActivityNotification2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityNotification2Binding = ActivityNotification2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityNotification2Binding.root)

        activityNotification2Binding.run {
            textViewNo2.text = intent.getIntExtra("data3", 0).toString()
            textViewNo2.append(intent.getStringExtra("data4"))

        }
    }
}