package com.test.android44_activityforresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android44_activityforresult.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var activityThirdBinding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityThirdBinding.root)

        activityThirdBinding.run {
            buttonThird.setOnClickListener {
                //Activity 종료
                finish()
            }
        }
    }
}