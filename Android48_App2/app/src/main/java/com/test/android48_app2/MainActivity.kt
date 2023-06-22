package com.test.android48_app2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android48_app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //다른 앱에 있는 액티비티 실행!



        activityMainBinding.run {

            val c1 = ActivityResultContracts.StartActivityForResult()
            val activityLauncher = registerForActivityResult(c1) {
                val value1 = it.data?.getIntExtra("value1", 0)
                val value2 = it.data?.getStringExtra("value2")

                textView.text = value1.toString()
                textView.append(value2)

            }

            button.setOnClickListener {
                //다른 앱의 activity에 붙여준 이름을 지정해서 intent생성
                val newIntent = Intent("com.test.android48_startactivityexport")
                newIntent.putExtra("data1", 100)
                newIntent.putExtra("data2", "안녕하세용")
                activityLauncher.launch(newIntent)

            }

            buttonShowMap.run{
                setOnClickListener {
                    // 위도와 경도를 문자열로 만들어준다.
//                    val address = "geo:37.243243,131.861601"
                    //웹사이트
                    val address = "http://developer.android.com"
                    val uri = Uri.parse(address)
                    //Intent.Action_view: 먼가를 보여주는 액티비티들이 공통으로 사용하는 이름
                    //ACTION_VIEW만 주면 address의 종류에 따라알아서 맵, 웹을 보여주는 건가?
                    val newIntent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(newIntent)
                }
            }

        }
    }
}