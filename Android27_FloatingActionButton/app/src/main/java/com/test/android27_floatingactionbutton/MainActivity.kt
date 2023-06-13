package com.test.android27_floatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android27_floatingactionbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            floatingActionButton.setOnClickListener {
                textView.text = "버튼을 눌렀삼"
            }
        }
    }
}