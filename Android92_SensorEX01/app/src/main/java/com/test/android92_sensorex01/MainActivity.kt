package com.test.android92_sensorex01

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android92_sensorex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    private var accelerometerValues = floatArrayOf(0f, 0f, 0f)
    private var magneticFieldValues = floatArrayOf(0f, 0f, 0f)

    // 센서로 부터 측정된 적이 있는지...
    private var isGetAcc = false
    private var isGetMag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //가속도 센서
        val accListener = object : SensorEventListener {
            override fun onSensorChanged(p0: SensorEvent) {
                //가속도 센서로부터 측정된 값 담아줌
                accelerometerValues = p0.values.clone()
                isGetAcc = true

                getAzimuth()
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            }

        }

        //자기장 센서
        val magListener = object : SensorEventListener {
            override fun onSensorChanged(p0: SensorEvent) {
                //가속도 센서로부터 측정된 값 담아줌
                magneticFieldValues = p0.values.clone()
                isGetMag = true

                getAzimuth()
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            }

        }

        //가속도 센서와 자기장 센서에 리스너 연결
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        val accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(accListener, accSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(magListener, magSensor, SensorManager.SENSOR_DELAY_UI)
    }

    //센서를 통해 측정된 값을 담을 리스너
    fun getAzimuth() {
        //두 가지로 행렬연산을 하는거여서 두 값이 모두 있어야 한다!
        if (isGetMag && isGetAcc) {
            //방위값 등을 계산하기 위해 사용할 배열
            val R = FloatArray(9) { 0f }
            val I = FloatArray(9) { 0f }

            //계산 행렬 값을 구한다
            SensorManager.getRotationMatrix(R, I, accelerometerValues, magneticFieldValues)
            //계산 행렬을 이용해 방위값 추출한다.
            val orientationValues = FloatArray(3) { 0f }
            SensorManager.getOrientation(R, orientationValues)

            //결과가 radiun값으로 나오기 때문에 각도 값으로 변환
            val azimuthDegrees = Math.toDegrees(orientationValues[0].toDouble())
            val pitch = Math.toDegrees(orientationValues[1].toDouble())
            val roll = Math.toDegrees(orientationValues[2].toDouble())

            activityMainBinding.run {
                textView.text = "방위값 : ${azimuthDegrees + 180}"
                textView2.text = "좌위 기울기 값 : $pitch"
                textView3.text = "앞뒤 기울기 값 : $roll)"
            }
            //이미지 뷰 회전
            activityMainBinding.imageView.rotation = (180 - azimuthDegrees).toFloat()
        }
    }
}