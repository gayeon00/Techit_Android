package com.test.android54_messagenotification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import com.test.android54_messagenotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val permissionList = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    lateinit var activityMainBinding: ActivityMainBinding

    val NOTIFICATION_CHANNEL1_ID = "Style"
    val NOTICIFATION_CHANNEL1_NAME = "Style Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //권한 확인
        requestPermissions(permissionList, 0)

        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTICIFATION_CHANNEL1_NAME)

        activityMainBinding.run {
            button.setOnClickListener {
                //BigPicture
                //접혔을 때
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Big Picture")
                builder.setContentText("Big Picture Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                //BigPicture 설정
                //메세지를 펼쳤을 때 이미지가 나타남
                val big = NotificationCompat.BigPictureStyle(builder)
                //보여줄 이미지 설정
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_android)
                big.bigPicture(bitmap)
                big.setBigContentTitle("Big Content Title")
                big.setSummaryText("Summary Text")

                val notification = builder.build()
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(10, notification)
            }

            button2.setOnClickListener {
                //Big Text
                //펼쳤을 때 장문의 문자열이 나온다
                //접었을 때
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Big Text")
                builder.setContentText("Big Text Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                //BigText 설정
                //펼쳤을 때
                val big = NotificationCompat.BigTextStyle(builder)
                big.setBigContentTitle("Big Content Title")
                big.setSummaryText("Summary Titme")
                big.bigText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

                val notification = builder.build()
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(10, notification)
            }

            button3.setOnClickListener {
                //InBox
                //한 줄의 문자열을 다수 보여줌
                //접었을 때
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("InBox")
                builder.setContentText("InBox Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                //InBox설정
                val inbox =NotificationCompat.InboxStyle(builder)
                inbox.setBigContentTitle("Inbox Style")
                inbox.setSummaryText("SummaryText")

                inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                inbox.addLine("bbbbbbbbbbbbbbbbbbbbbbbbb")
                inbox.addLine("cccccccccccccccccccccccccccc")
                inbox.addLine("ddddddddddddddddddddddddddddddd")

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(30, notification)
            }

            button4.setOnClickListener {
                //Message
                //채팅 화면처럼 구성할 수 있다.
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Message")
                builder.setContentText("Message Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                //사람1
                val personBuilder1 = Person.Builder()
                val icon1 =IconCompat.createWithResource(this@MainActivity, android.R.drawable.ic_secure)
                personBuilder1.setIcon(icon1)
                personBuilder1.setName("홍길동")
                val person1 = personBuilder1.build()

                //사람2
                val personBuilder2 = Person.Builder()
                val icon2 =IconCompat.createWithResource(this@MainActivity, android.R.drawable.ic_btn_speak_now)
                personBuilder2.setIcon(icon2)
                personBuilder2.setName("김길동")
                val person2 = personBuilder2.build()

                //Message Style 설정
                val msg = NotificationCompat.MessagingStyle(person1) //채팅방의 주체를 넣어줌

                //대화 내용을 설정
                msg.addMessage("첫 번째 메시지", System.currentTimeMillis(), person1)
                msg.addMessage("두 번째 메시지", System.currentTimeMillis(), person2)
                msg.addMessage("세 번째 메시지", System.currentTimeMillis(), person1)
                msg.addMessage("네 번째 메시지", System.currentTimeMillis(), person2)

                builder.setStyle(msg)
                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(30, notification)
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