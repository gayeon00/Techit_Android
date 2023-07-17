package com.test.android90_sensorlist

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android90_sensorlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //센서 관리자 가져오기
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        //단말기에 있는 센서 목록 가져옴
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        activityMainBinding.textView.run {
            text = ""

            //센서의 수 만큼 반복
            for (sensor in sensorList) {
                append("센서 이름 : ${sensor.name}\n")
                append("센서 종류 : ${sensor.type}\n\n")
            }
        }




    }
}