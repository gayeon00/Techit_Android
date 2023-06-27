package com.test.android57_service

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.test.android57_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //서비스를 가동시키기 위해 사용할 인텐트
    lateinit var serviceIntent2: Intent

    //동작중인 서비스 객체를담을 변수
    var ipcService: TestService? = null

    //서비스 접속을 관리하는 객체
    lateinit var myServiceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                //현재 서비스가 실행중인지 파악
                val chk = isServiceRunning("com.test.android57_service.TestService")
                serviceIntent2 = Intent(this@MainActivity, TestService::class.java)

                //서비스가 가동중이 아니라면 서비스 가동
                if (!chk) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(serviceIntent2)
                    } else {
                        startService(serviceIntent2)
                    }
                }

                //서비스에 접속
                myServiceConnection = MyServiceConnection()
                bindService(serviceIntent2, myServiceConnection, BIND_ADJUST_WITH_ACTIVITY)
            }

            button2.setOnClickListener {
                stopService(serviceIntent2)
            }

            button3.setOnClickListener {
                // 서비스에서 값을 가져오기
                val value = ipcService?.getNumber()
                activityMainBinding.textView.text = "value: $value"
            }
        }
    }

    //서비스가 가동중인지 확인하는 메서드
    private fun isServiceRunning(name: String): Boolean {
        //서비스 관리자 추출
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        // 현재 실행중인 모든 서비스를 가져온다.
        val serviceList = activityManager.getRunningServices(Int.MAX_VALUE)
        // 가져온 서비스의 수 만큼 반복한다.
        for (serviceInfo in serviceList) {
            // 현재의 서비스의 클래스 이름이 매개변수로 전달된 클래스 이름과 동일한지...
            if (serviceInfo.service.className == name) {
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()

        //서비스가 접속중이라면 해제
        if (::myServiceConnection.isInitialized) {
            unbindService(myServiceConnection)
        }
    }

    //Activity의 서비스 접속을 관리하는 클래스
    inner class MyServiceConnection : ServiceConnection {
        //서비스 접속이 성공했을 경우
        // 두 번째 매개변수: 서비스의 onBind메서드가 반환하는 객체
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            //서비스 추출
            ipcService = (p1 as TestService.LocalBinder).getService()
        }

        //서비스 접속이 해제됐을 때
        override fun onServiceDisconnected(p0: ComponentName?) {
            ipcService = null
        }

    }
}