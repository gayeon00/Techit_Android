package com.test.android74_filesystemex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android74_filesystemex01.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    lateinit var activityShowBinding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityShowBinding.root)

        val person = intent.getSerializableExtra("person") as Person

        activityShowBinding.run {
            textViewName.text = person.name
            textViewAge.text = person.age.toString()
            textViewKor.text = person.age.toString()
        }
    }
}