package com.test.android71_viewex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android71_viewex01.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    lateinit var activityInputBinding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityInputBinding.root)

        activityInputBinding.run {
            toolbar2.run {
                inflateMenu(R.menu.input)

                setOnMenuItemClickListener {
                    //저장 버튼을 클릭하면 할 일

                    false
                }
            }
        }
    }
}