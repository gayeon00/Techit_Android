package com.test.android86_localization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android86_localization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            textView2.setText(R.string.str1)
            imageView2.setImageResource(R.drawable.flag1)
        }
    }
}