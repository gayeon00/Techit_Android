package com.test.android80_miniproject03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.test.android80_miniproject03.databinding.ActivityMainBinding
import com.test.android80_miniproject03.user.LogInActivity
import com.test.android80_miniproject03.user.SignUpActivity
import com.test.android80_miniproject03.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            buttonLogIn.setOnClickListener {
                val logInIntent = Intent(this@MainActivity, LogInActivity::class.java)
                startActivity(logInIntent)
            }

            buttonSignUp.setOnClickListener {
                val signUpIntent = Intent(this@MainActivity, SignUpActivity::class.java)
                startActivity(signUpIntent)
            }
        }
    }
}