package com.test.android56_broadcastreceiverprac

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast
import java.text.Format

class TestReceiver : BroadcastReceiver() {

    //받으면 실행하는 코드
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        when(intent.action) {
            //부팅 완료 이벤트를 받은 경우
            "android.intent.action.BOOT_COMPLETED" -> {
                Toast.makeText(context, "부팅 완료", Toast.LENGTH_SHORT).show()
            }

            //문자 수신 이벤트를 받은 경우
            "android.provider.Telephony.SMS_RECEIVED" -> {
                if(intent.extras!=null) {
                    val objs = intent.extras?.get("pdus") as Array<*>

                    for(obj in objs){
                        //문자 메시지 객체 추출
                        val format = intent.extras?.getString("format")
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