package com.test.android89_deviceinfo

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.test.android89_deviceinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val permissionList = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_PHONE_NUMBERS,
        Manifest.permission.READ_SMS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList, 0)

        //TelephonyManager 추출
        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        activityMainBinding.run {

            //권한 허용 여부를 확인해 모두 허용돼있어야단말기 정보를 출력한다.

            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.READ_SMS
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.READ_PHONE_NUMBERS
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                //전화번호
                textView.text = "전화 번호 : ${telephonyManager.line1Number}\n"
                //sim 국가 코드
                textView.append("sim 국가 코드 : ${telephonyManager.simCountryIso}\n")
                //모바일 국가코드 + 모바일 네트워크 코드
                textView.append("모바일 국가 코드 + 모바일 네트워크 코드 : ${telephonyManager.simOperator}\n")
                //서비스 이름
                textView.append("서비스 이름 : ${telephonyManager.simOperatorName}\n")
                //sim상태 (통신 가능 여부, pin lock 여부)
                textView.append("sim 상태 : ${telephonyManager.simState}\n")
                //음성 메일 번호
                textView.append("음성 메일 번호 : ${telephonyManager.voiceMailNumber}\n")

            }

            //보드 이름
            textView.append("보드 이름 : ${Build.BOARD}\n")
            //소프트웨어를 커스터마이징한 회사
            textView.append("소프트웨어를 커스터마이징한 회사 : ${Build.BRAND}\n")
            //제조사 디자인 명
            textView.append("제조사 디자인 명 : ${Build.DEVICE}\n")
            //사용자에게 표시되는 빌드 ID
            textView.append("사용자에게 표시되는 빌드 ID : ${Build.DISPLAY}\n")
            //빌드 고유 ID
            textView.append("빌드 고유 ID : ${Build.FINGERPRINT}\n")
            //ChangeList 번호
            textView.append("ChangeList 번호 : ${Build.ID}\n")
            //제품 하드웨어 제조 업체
            textView.append("제품 하드웨어 제조 업체 : ${Build.MANUFACTURER}\n")
            //제품 모델 명
            textView.append("제품 모델 명 : ${Build.MODEL}\n")
            //제품 명
            textView.append("제품 명 : ${Build.PRODUCT}\n")
            //빌드 구분
            textView.append("빌드 구분 : ${Build.TAGS}\n")
            //빌드 타입
            textView.append("빌드 타입 : ${Build.TYPE}\n")
            //안드로이드 버전
            textView.append("안드로이드 버전 : ${Build.VERSION.RELEASE}\n")
            //안드로이드 코드 네임
            textView.append("안드로이드 코드 네임 : ${Build.VERSION.CODENAME}\n")
            //안드로이드 API 레벨
            textView.append("안드로이드 API 레벨 : ${Build.VERSION.SDK_INT}\n")

            //단말기 해상도 정보
            //해상도 정보를 가지고 있는 객체를 추출
            val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

            //안드로이드 버전으로분기
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                //단말기의 가로 해상도 가져오기
                val width = windowManager.currentWindowMetrics.bounds.width()
                //세로 해상도
                val height = windowManager.currentWindowMetrics.bounds.height()

                textView.append("가로 해상도 : $width\n")
                textView.append("세로 해상도 : $height\n")
            } else {
                //해상도 정보를 담을 객체
                val point = Point()
                //해상도 정보담기
                windowManager.defaultDisplay.getSize(point)

                textView.append("가로 해상도 : ${point.x}\n")
                textView.append("세로 해상도 : ${point.y}\n")
            }



        }

    }
}