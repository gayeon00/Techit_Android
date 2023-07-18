package com.test.android96_socketclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android96_socketclient.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        activityMainBinding.run {
            button.setOnClickListener {
                //네트워크 관련 코드는 무조건 스레드로 돌리기
                //특히 모바일은 통신에 문제가 발생할 가능성이 높기 때문에
                //오류 발생 시 안드로이드 앱 자체가 종료되는 것을 예방하기 위함!
                thread {
                    //소켓 객체 생성
                    //127.0.0.1은 지금 프로그램이 돌아가고 있는 디바이스(에뮬레이터)를 가리키는 아이피
                    // -> 우리는 컴퓨터에서 실행시켜놓은 서버에 접속해야하니까
                    // 공유기 상 아이피가 필요함!
                    //국가별로 사용할 수있는 IP 할당(각 국당 몇개씩)
                    val socket = Socket(SERVER_IP, SERVER_PORT)

                    //서버와 연결된 스트림 추출
                    val inputStream = socket.getInputStream()
                    val dataInputStream = DataInputStream(inputStream)

                    val outputStream = socket.getOutputStream()
                    val dataOutputStream = DataOutputStream(outputStream)

                    //서버로부터 데이터 받기
                    val data1 = dataInputStream.readInt()
                    val data2 = dataInputStream.readDouble()
                    val data3 = dataInputStream.readBoolean()
                    val data4 = dataInputStream.readUTF()

                    //서버에게 데이터 전달
                    dataOutputStream.writeInt(200)
                    dataOutputStream.writeDouble(22.22)
                    dataOutputStream.writeBoolean(false)
                    dataOutputStream.writeUTF("클라이언트가 보낸 문자열")

                    println("data1 : $data1")
                    println("data2 : $data2")
                    println("data3 : $data3")
                    println("data4 : $data4")


                    //화면 관련 작업은 이 스레드에서!!
                    runOnUiThread {
                        textView.text = "data1 : $data1\n"
                        textView.append("data2 : $data2\n")
                        textView.append("data3 : $data3\n")
                        textView.append("data4 : $data4")
                    }

                    socket.close()
                }
            }
        }

    }

    companion object {
        const val SERVER_IP = "192.168.219.104"
        const val SERVER_PORT = 55555
    }
}