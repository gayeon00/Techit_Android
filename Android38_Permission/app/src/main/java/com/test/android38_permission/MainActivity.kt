package com.test.android38_permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android38_permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    //확인 받을 권한 목록
    //이 권한 목록 중 확인이 불필요하거나 이미 허용되있는 권한은 제외
    val permissionList = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // 모든 권한에 대해서 한번에 요청하고자 한다면 requestPermission 메서드를 사용하고
    // 권한 확인 후에 처리가 필요하다면 onRequestPermissionsResult 메서드를 overriding하고
    // 권한별로 분기하여 처리하면 된다.
    // 만약, 권한 요청 후 필요한 처리를 권한별로 나눠서 구현하고 싶다면 ActivityResultCallback을 사용한다.
    //(원래는 다른 용도로 사용)

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //onCreate안에서 만들어야함
        //위치 정보 권한 확인을 위한 객체들을 생성
        val r1 = ActivityResultContracts.RequestMultiplePermissions()
        val callback1 = LocationPermissionCallBack()
        val locationLauncher = registerForActivityResult(r1, callback1)

        val r2 = ActivityResultContracts.RequestMultiplePermissions()
        val callback2 = ContactPermissionCallback()
        val contactLauncher = registerForActivityResult(r2, callback2)

        activityMainBinding.run {
            button.setOnClickListener {
                //권한 확인을 요청한다.
                //보통 앱이 처음 로드될 때 물어봄
                //이게 젤 간단한 방법! 이거 하나만 써도 ㄱㅊ!!!
                requestPermissions(permissionList, 0)
            }

            button2.setOnClickListener {
                //권한 요청
                val a1 = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                locationLauncher.launch(a1)
            }

            button3.setOnClickListener {
                val a1 = arrayOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS
                )

                contactLauncher.launch(a1)
            }
        }
    }

    //requestPermissions 메서드를 통해 권한을 요청하여 요청 작업이 끝나면 호출되는 메서드
    //권한 확인 후 할일이 있을 때만 구현해주면 됨

    //activity가 갖고있는메서드 -> 각 권한별로 메서드를 분리할 수없음 => 불편!
    override fun onRequestPermissionsResult(
        //필요할 때만 권한 획득을 할 때 숫자를 다르게 해서 권한을 구분함
        requestCode: Int,
        //모든권한목록
        permissions: Array<out String>,
        //권한 허용 여부 결과
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        activityMainBinding.run {
            textView.text = ""

            for (idx in permissions.indices) {
                //이렇게 각 권한에 대해 분기처리함!
                //현재 idx의 권한 이름 가져오기
                val p1 = permissions[idx]
                //권한 허용 여부 값을 가져옴
                val g1 = grantResults[idx]
                when (p1) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("위치 1: 허용\n")
                        } else {
                            textView.append("위치 1: 거부\n")
                        }
                    }

                    Manifest.permission.ACCESS_COARSE_LOCATION -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("위치 2: 허용\n")
                        } else {
                            textView.append("위치 2: 거부\n")
                        }
                    }

                    Manifest.permission.READ_CONTACTS -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("연락처 읽기: 허용\n")
                        } else {
                            textView.append("연락처 읽기: 거부\n")
                        }
                    }

                    Manifest.permission.WRITE_CONTACTS -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("연락처 쓰기: 허용\n")
                        } else {
                            textView.append("연락처 쓰기: 거부\n")
                        }
                    }

                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("외부 저장소 읽기: 허용\n")
                        } else {
                            textView.append("외부 저장소 읽기: 거부\n")
                        }
                    }

                    Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("외부 저장소 쓰기: 허용\n")
                        } else {
                            textView.append("외부 저장소 쓰기: 거부\n")
                        }
                    }

                    Manifest.permission.INTERNET -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("인터넷: 허용\n")
                        } else {
                            textView.append("인터넷: 거부\n")
                        }
                    }
                }
            }
        }
    }

    //위치 용으로만 사용하는 권한 확인후 일처리 클래스
    inner class LocationPermissionCallBack : ActivityResultCallback<Map<String, Boolean>> {
        //Map에는 권한의 이름과 권한 허용 여부 값을 들여온다.
        override fun onActivityResult(result: Map<String, Boolean>?) {
            activityMainBinding.run {
                textView.text = "위치 권한 확인\n"

                if (result != null) {
                    //확인할 권한 만큼 반복
                    for (key in result.keys) {
                        //권한 만큼 반복
                        when (key) {
                            Manifest.permission.ACCESS_FINE_LOCATION -> {
                                if (result[key] == true) {
                                    textView.append("위치 1 권한 허용\n")
                                } else {
                                    textView.append("위치 1 권한 거부\n")
                                }
                            }

                            Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                if (result[key] == true) {
                                    textView.append("위치 2 권한 허용\n")
                                } else {
                                    textView.append("위치 2 권한 거부\n")
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    inner class ContactPermissionCallback : ActivityResultCallback<Map<String, Boolean>> {
        override fun onActivityResult(result: Map<String, Boolean>?) {
            activityMainBinding.run {
                textView.text = "연락처 권한 확인\n"

                if (result != null) {
                    for (permission in result.keys) {
                        when (permission) {
                            Manifest.permission.READ_CONTACTS -> {
                                if (result[permission] == true) textView.append("연락처 읽기 권한 허용\n")
                                else textView.append("연락처 읽기 권한 거부\n")
                            }

                            Manifest.permission.WRITE_CONTACTS -> {
                                if (result[permission] == true) textView.append("연락처 쓰기 권한 허용\n")
                                else textView.append("연락처 쓰기 권한 거부\n")
                            }
                        }
                    }
                }


            }
        }
    }
}