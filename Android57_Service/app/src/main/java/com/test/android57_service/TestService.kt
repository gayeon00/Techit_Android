package com.test.android57_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class TestService : Service() {

    val NOTIFICATION_CHANNEL1_ID = "Service"
    val NOTIFICATION_CHANNEL1_NAME = "Service"

    var isRunning = false
    var value = 0

    //Activity가 서비스에 접속하면 전달될 바인더 객체
    val binder = LocalBinder()

    //외부에서 서비스에 접속하면 자동으로 호출되는 메서드
    //여기에서는 바인더 객체를 반환한다.
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    //서비스가 가동되면 자동으로 호출되는 메서드
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)

        //안드로이드 8.0이상이라면
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setContentTitle("서비스 가동")
            builder.setContentText("서비스가 가동 중입니다")
            val notification = builder.build()
            //포그라운드 서비스용이라
            startForeground(10, notification)
        }

        isRunning = true
        thread {
            while (isRunning) {
                SystemClock.sleep(500)
                val now = System.currentTimeMillis()
                Log.d("now", "now : $now")
                value++
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    //서비스가 중지되면 호출되는 메서드
    override fun onDestroy() {
        super.onDestroy()
        //스레드의 while문 중단을 위해
        isRunning = false
    }

    //Notification Channel을 등록하는 메서드
    //첫 번째: 코드에서 채널을 관리하기 위한 이름
    //두 번째: 사용자에게 노출시킬 이름
    fun addNotificationChannel(id: String, name: String) {
        //안드로이드 8.0 이상일 때만 동작하도록
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //알림 메세지 관리하는 객체추출
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            //id통해 notificationchannel 객체 추출
            //채널이 등록된 적이 없다면 null반환
            val channel = notificationManager.getNotificationChannel(id)
            //채널이 등록된 적이 없다면
            if (channel == null) {
                //채널 객체 생성
                val newChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
                //단말기에 LED 램프가 있다면 램프 사용 하도록 설정
                newChannel.enableLights(true)
                //LED램프 색상 설정
                newChannel.lightColor = Color.RED
                //진동 사용 여부
                newChannel.enableVibration(true)
                //채널을 등록
                notificationManager.createNotificationChannel(newChannel)
            }
        }
    }

    //notification 메시지 관리 객체 생성하는 메서드
    //notification 채널 id받기
    fun getNotificationBuilder(id: String): NotificationCompat.Builder {
        //안드로이드 8.0이상이면
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, id)
        } else {
            NotificationCompat.Builder(this)
        }
    }

    //접속하는 Activity에서 서비스를 추출하기 위해 사용하는 객체
    inner class LocalBinder : Binder() {
        public fun getService(): TestService {
            return this@TestService
        }
    }

    //변수의 값을 반환하는 메서드
    public fun getNumber(): Int {
        return value
    }
}