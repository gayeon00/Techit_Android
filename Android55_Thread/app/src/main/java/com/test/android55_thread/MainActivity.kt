package com.test.android55_thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import com.test.android55_thread.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                val now = System.currentTimeMillis()
                textView.text = "now: $now"
            }
        }

        // 100 ms 마다 한번씩 현재 시간을 가져와 출력한다.
        //얘가 무한으로 돌아가서 onCreate는 영원히 끝나지 않음 -> 화면에 아무것도 뜨지 않음;;;
        //MainThread 내부 코드가 3초 이상 돌아가게 되면 안됨!!!
//        while (true) {
//            SystemClock.sleep(100)
//            val now2 = System.currentTimeMillis()
//            activityMainBinding.textView2.text = "now: $now2"
//        }

        // 사용자가 발생시킨 쓰래드 (화면 관련 작업 금지)
        thread {
            while (true) {
                SystemClock.sleep(100)
                val now2 = System.currentTimeMillis()
//                Log.d("now2", "now: $now2")
                // 사용자가 발생 시킨 쓰래드 안에서 화면에 관련된 작업이 필요하다면
                // runOnUiThread를 사용한다.
                // 여기 안에 만들어놓은 코드는 MainThread가 처리하도록 요청한다.
                // Kotlin 기반으로 안드로이드 애플리케이션을 제작할 경우
                // runOnUiThread를 사용하는 것을 미리 구현해놨기 때문에
                // runOnUiThraad를 사용하지 않아도 된다.
                runOnUiThread {
                    activityMainBinding.textView2.text = "now2 : $now2"
                }
            }
        }
    }
}