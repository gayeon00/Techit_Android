package com.test.android56_app2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class App2Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val str1 = "App2의 BR이 동작했슴다"
        val t1 = Toast.makeText(context, str1, Toast.LENGTH_SHORT)
        t1.show()
    }
}