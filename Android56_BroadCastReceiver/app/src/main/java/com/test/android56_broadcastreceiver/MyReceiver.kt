package com.test.android56_broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    //BR가 동작할 때 자동으로 호출되는 메서드
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val str1 = "브로드캐스트 리시버가 동작했습니다."
        val t1 = Toast.makeText(context, str1, Toast.LENGTH_SHORT)
        t1.show()
    }
}