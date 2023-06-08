package com.test.android03_viewbindingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android03_viewbindingex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button1.setOnClickListener {
                var result = 0
                for (i in 1..100) {
                    result += i
                }

                textView.text = result.toString()
            }

            button2.setOnClickListener {
                var result = 0
                for (i in 101..200) {
                    result += i
                }

                textView.text = result.toString()
            }
        }
    }
}