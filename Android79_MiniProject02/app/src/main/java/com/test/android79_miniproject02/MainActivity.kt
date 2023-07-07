package com.test.android79_miniproject02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android79_miniproject02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        if (checkPwdSetting()) {
            logIn()
        } else {
            setPassword()
        }
    }

    private fun setPassword() {
        TODO("Not yet implemented")
    }

    private fun logIn() {
        TODO("Not yet implemented")
    }

    private fun checkPwdSetting(): Boolean {
        if(db가 없는 경우) {
            return false
        } else {

        }
    }
}