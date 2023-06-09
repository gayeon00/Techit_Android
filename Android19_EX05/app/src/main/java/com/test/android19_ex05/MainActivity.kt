package com.test.android19_ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.test.android19_ex05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        var displayText = ""
        activityMainBinding.run {
            textViewDisplay.run{
                addTextChangedListener {
                    displayText = text.toString()
                }
            }

            button0.setOnClickListener {
                if (textViewDisplay.text != "0") {
                    textViewDisplay.append("0")
                }

            }
            button1.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "1"
                } else {
                    textViewDisplay.append("1")
                }
            }
            button2.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "2"
                } else {
                    textViewDisplay.append("2")
                }
            }
            button3.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "3"
                } else {
                    textViewDisplay.append("3")
                }
            }
            button4.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "4"
                } else {
                    textViewDisplay.append("4")
                }
            }
            button5.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "5"
                } else {
                    textViewDisplay.append("5")
                }
            }
            button6.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "6"
                } else {
                    textViewDisplay.append("6")
                }
            }
            button7.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "7"
                } else {
                    textViewDisplay.append("7")
                }
            }
            button8.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "8"
                } else {
                    textViewDisplay.append("8")
                }
            }
            button9.setOnClickListener {
                if (textViewDisplay.text == "0") {
                    textViewDisplay.text = "9"
                } else {
                    textViewDisplay.append("9")
                }
            }

            buttonReset.setOnClickListener {
                textViewDisplay.text = ""
            }
        }
    }
}