package com.test.android72_applicationclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android72_applicationclass.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.run {
            val application = application as MainApplication

            textViewSecond.text = "${application.value1}\n";
            textViewSecond.append("${application.value2}\n");
            textViewSecond.append("${application.value3}\n");

            buttonSecond.setOnClickListener {
                finish()
            }
        }



    }
}