package com.test.android93_gps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.test.android93_gps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    private val permissionList = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    )
    lateinit var locationManager: LocationManager

    //위치 측정 리스너
    var locationListener: LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList, 0)

        activityMainBinding.run {
            button.setOnClickListener {
                //사용자의 권한 허용 여부 확인
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    //위치 정보를 관리하는 매니저 추출
                    locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

                    //기존에 측정한 위치정보 가져오기 (GPS나 NETWORK로 측정된 게 없을 경우 PASSIVE 사용 -> 거의 사용 안함!)
                    val location1 =
                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    val location2 =
                        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                    //기존에 추출된 정보가 있다면 일단 이것으로 먼저 사용
                    if (location1 != null) {
                        showInfo(location1)
                    } else if (location2 != null) {
                        showInfo(location2)
                    }

                    //위치 측정 리스너(중단 기능을 넣으려고 따로 만들어줌)
                    locationListener = LocationListener {
                        //위치가 새롭게 측정되면 호출되는 메서드
                        showInfo(it)
                    }

                    //GPS Provider가 사용가능하다면 측정 요청
                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        // 첫 번째 : 요청할 프로바이더
                        // 두 번째 : 측정할 시간 주기. 0을 넣어주면 가장 짧은 주기마다 측정을 한다. (단위 ms)
                        // 세 번째 : 측정할 거리 단위. 0을 넣어주면 가장 짧은 거리마다 측정을 한다. (단위 m)
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, 0, 0f, locationListener!!
                        )
                    }

                    //Network Provider가 사용가능하다면 측정 요청
                    if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                        locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener!!
                        )
                    }
                } else {
                    requestPermissions(permissionList, 0)
                }

            }

            button2.setOnClickListener {
                //위치 측정 중단
                if (locationListener != null) {
                    locationManager.removeUpdates(locationListener!!)
                    locationListener = null
                }
            }
        }
    }

    //위도와 경로 출력
    private fun showInfo(location: Location?) {
        if (location != null) {
            activityMainBinding.run {
                textView.text = "Provieder : ${location.provider}"
                textView2.text = "위도 : ${location.latitude}"
                textView3.text = "경도 : ${location.longitude}"
            }
        }
    }
}