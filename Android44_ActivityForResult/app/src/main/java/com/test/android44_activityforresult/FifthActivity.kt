package com.test.android44_activityforresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android44_activityforresult.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {
    lateinit var activityFifthBinding: ActivityFifthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityFifthBinding = ActivityFifthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityFifthBinding.root)

        activityFifthBinding.run {
            buttonFifth.setOnClickListener {
                finish()
            }
        }
    }
}