package com.test.android52_notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.test.android52_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //확인할 권한
    //안드로이드 13부터는 노티피케이션 사용 위해 POST_NOTIFICATIONS권한을 사용자로부터 확인받아야함
    val permissionList = arrayOf(Manifest.permission.POST_NOTIFICATIONS)

    //Notification channel을 코드에서 구분하기 위한 이름
    val NOTIFICATION_CHANNEL1_ID = "CHANNEL1"
    val NOTIFICATION_CHANNEL2_ID = "CHANNEL2"

    //사용자에게 노출 시킬 이름
    val NOTIFICATION_CHANNEL1_NAME = "첫 번째 채널"
    val NOTIFICATION_CHANNEL2_NAME = "두 번째 채널"

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //권한 확인 요청
        requestPermissions(permissionList, 0)

        //notificationchannel등록
        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)
        addNotificationChannel(NOTIFICATION_CHANNEL2_ID, NOTIFICATION_CHANNEL2_NAME)

        activityMainBinding.run {
            button.setOnClickListener {
                //Notificationbuilder 가져오기
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                //작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                //큰 아이콘 -> 비트맵 객체받음
                val bigIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                builder.setLargeIcon(bigIcon)
                //숫자 설정
                builder.setNumber(100)
                //타이틀 설정
                builder.setContentTitle("Content Title1")
                //메시지 설정
                builder.setContentText("Content Text 1")

                //메시지 객체 생성
                val notification = builder.build()
                //알림 메세지를 관리하는 객체 추출
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                // 첫번째 매개변수에 넣어주는 정수는 단말기 전체에서 메시지를 구분하기 위한 값
                // 값은 값으로 메시지를 계속 보여주면 메시지가 갱신 것이고
                // 다른 값으로 메시즈를 계속 보여주면 메시지가 각각 따로 나타난다.
                notificationManager.notify(10, notification)
            }

            button2.setOnClickListener {
                //Notificationbuilder 가져오기
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                //작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                //타이틀 설정
                builder.setContentTitle("Content Title2")
                //메시지 설정
                builder.setContentText("Content Text 2")

                //메시지 객체 생성
                val notification = builder.build()
                //알림 메세지를 관리하는 객체 추출
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                // 첫번째 매개변수에 넣어주는 정수는 단말기 전체에서 메시지를 구분하기 위한 값
                // 값은 값으로 메시지를 계속 보여주면 메시지가 갱신 것이고
                // 다른 값으로 메시즈를 계속 보여주면 메시지가 각각 따로 나타난다.
                notificationManager.notify(20, notification)
            }

            button3.setOnClickListener {
                //Notificationbuilder 가져오기
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL2_ID)
                //작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                //타이틀 설정
                builder.setContentTitle("Content Title3")
                //메시지 설정
                builder.setContentText("Content Text 3")

                //메시지 객체 생성
                val notification = builder.build()
                //알림 메세지를 관리하는 객체 추출
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                // 첫번째 매개변수에 넣어주는 정수는 단말기 전체에서 메시지를 구분하기 위한 값
                // 값은 값으로 메시지를 계속 보여주면 메시지가 갱신 것이고
                // 다른 값으로 메시즈를 계속 보여주면 메시지가 각각 따로 나타난다.
                notificationManager.notify(30, notification)
            }

            button4.setOnClickListener {
                //Notificationbuilder 가져오기
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL2_ID)
                //작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                //타이틀 설정
                builder.setContentTitle("Content Title4")
                //메시지 설정
                builder.setContentText("Content Text 4")

                //메시지 객체 생성
                val notification = builder.build()
                //알림 메세지를 관리하는 객체 추출
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                // 첫번째 매개변수에 넣어주는 정수는 단말기 전체에서 메시지를 구분하기 위한 값
                // 값은 값으로 메시지를 계속 보여주면 메시지가 갱신 것이고
                // 다른 값으로 메시즈를 계속 보여주면 메시지가 각각 따로 나타난다.
                notificationManager.notify(40, notification)
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