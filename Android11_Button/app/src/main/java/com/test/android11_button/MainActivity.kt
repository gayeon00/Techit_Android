package com.test.android11_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android11_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            button2.run{
                //버튼의 문자열 변경
                text = "버튼입니당"
                //버튼을 눌렀을 때 반응하는 리스너
                setOnClickListener {
                    textView.text = "버튼을 눌렀슴당"
                }
            }


            imageButton3.run{
                //이미지 버튼의 이미지 변경
                //setImageBitmap, setImageDrawable중 setImageBitmap쓰자!!
                setImageResource(R.drawable.imgflag8)

                setOnClickListener{
                    textView.text = "이미지 버튼을 눌렀슴당"
                }
            }

        }


    }
}