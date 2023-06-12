package com.test.android20_viewhide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.android20_viewhide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            imageView3.visibility = View.INVISIBLE

            button.setOnClickListener {
                imageView.visibility = View.VISIBLE
                //안보이고 자리 차지
//                    imageView3.visibility = View.INVISIBLE
                //안보이고 자리도 안차지
                imageView3.visibility = View.GONE
            }

            button2.setOnClickListener {
                imageView.visibility = View.INVISIBLE
//                    imageView3.visibility = View.VISIBLE
                imageView3.visibility = View.GONE
            }
        }
    }
}