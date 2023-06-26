package com.test.android56_broadcastreceiver

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android56_broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    private val permissionList = arrayOf(
        Manifest.permission.RECEIVE_BOOT_COMPLETED,
        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.READ_PHONE_STATE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList, 0)

        activityMainBinding.run {
            button.setOnClickListener {
                // 클래스 이름을 지정하여 같은 앱에 있는 BR을 동작시킴
                val brIntent = Intent(this@MainActivity, MyReceiver::class.java)
                sendBroadcast(brIntent)
            }

            button2.setOnClickListener {
            //다른 앱이 가지고 있는 BR중에 동작시키고 싶은 BR의 이름을 지정 (개발자 지정 BR의 경우)
            //이름은 manifest에서 확인
                //해당 앱이 실행 안된 상태에서는 동작이 안됨 -> 사실상 의미 없음!
                val app2BrIntent = Intent("com.test.testbr")
                sendBroadcast(app2BrIntent)
            }
        }
    }
}