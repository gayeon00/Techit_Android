package com.test.android21_compoundcomponent2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android21_compoundcomponent2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                textView.text = if (toggleButton.isChecked) {
                    "On 상태임다"
                } else {
                    "Off 상태임다."
                }
            }

            button2.setOnClickListener {
                toggleButton.isChecked = true
            }

            button3.setOnClickListener {
                toggleButton.isChecked = false
            }

            button4.setOnClickListener {
                toggleButton.toggle()
            }

            //on/off상태가 변경될 때 리스너
            toggleButton.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    textView2.text = "ToggleButton이 On 상태"
                } else {
                    textView2.text = "ToggleButton이 Off상태"
                }
            }
        }
    }
}