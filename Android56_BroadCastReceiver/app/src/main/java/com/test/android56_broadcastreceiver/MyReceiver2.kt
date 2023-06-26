package com.test.android56_broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast

class MyReceiver2 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        //BR을 등록했을 때 사용한 이름을 ㅏㄱ져옴
        when (intent.action) {
            //부팅 완료
            //기기의 부팅이 완료되면 자동으로 호출됨
            "android.intent.action.BOOT_COMPLETED" -> {
                Toast.makeText(context, "부팅 완료", Toast.LENGTH_SHORT).show()
            }

            "android.provider.Telephony.SMS_RECEIVED" -> {
                //수신 문자를 가지고 있는 객체를 추출
                if (intent.extras != null) {
                    val objs = intent.extras?.get("pdus") as Array<*>
                    if (objs != null) {
                        //추출한 메시지 수 만큼 반복
                        for (obj in objs) {
                            //문자 메시지 객체 추출
                            val format = intent.extras?.getString("format")
                            //문자 메시지 객체 생성
                            val currentSMS = SmsMessage.createFromPdu(obj as ByteArray?, format)

                            val str1 = """전화번호 : ${currentSMS.displayOriginatingAddress}
                                | 내용 : ${currentSMS.displayMessageBody}
                            """.trimMargin()

                            Toast.makeText(context, str1, Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }

        }
    }
}