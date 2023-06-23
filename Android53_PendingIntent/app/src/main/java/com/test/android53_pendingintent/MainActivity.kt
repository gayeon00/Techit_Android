package com.test.android53_pendingintent

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.test.android53_pendingintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val permissionList = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    val NOTIFICATION_CHANNEL1_ID = "Pending"
    val NOTIFICATION_CHANNEL1_NAME = "Pending Intent"

    lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList, 0)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)

        activityMainBinding.run {
            button.setOnClickListener {
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Notification 1")
                builder.setContentText("알림 메시지 1")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                //메시지 터치하면 자동으로 메시지 제거
                builder.setAutoCancel(true)

                //메시지를 터치하면 액티비티가 실행되도록!
                val newIntent = Intent(this@MainActivity, Notification1Activity::class.java)
                newIntent.putExtra("data1", 100)
                newIntent.putExtra("data2", "안녕하세요")
                val pendingIntent1 = PendingIntent.getActivity(
                    this@MainActivity,
                    10,
                    newIntent,
                    //액티비티 실행에 관련된 옵션 설정
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                builder.setContentIntent(pendingIntent1)

                //Action설정한 인텐트
                val newIntent2 = Intent(this@MainActivity, Notification2Activity::class.java)
                newIntent2.putExtra("data3", 200)
                newIntent2.putExtra("data4", "반갑습니당")

                val pendingIntent2 = PendingIntent.getActivity(
                    this@MainActivity,
                    100,
                    newIntent2,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                //action 생성
                val builder2 = NotificationCompat.Action.Builder(
                    android.R.drawable.ic_dialog_alert,
                    "Action",
                    pendingIntent2
                )
                val action2 = builder2.build()
                builder.addAction(action2)

                val notification = builder.build()
                notificationManager.notify(10, notification)
            }
        }
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
}