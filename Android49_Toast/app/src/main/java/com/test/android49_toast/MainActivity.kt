package com.test.android49_toast

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.test.android49_toast.databinding.ActivityMainBinding
import com.test.android49_toast.databinding.SnackbarBinding
import com.test.android49_toast.databinding.ToastBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var snackBar1: Snackbar
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                //메시지를 OS가 띄우기 때문에, 앱이 종료돼도 토스트는 계속 나옴
                val t1 = Toast.makeText(this@MainActivity, "hello", Toast.LENGTH_SHORT)
                t1.show()

                //메시지가 사라질 때 동작할 코드가 있다면
                //Android 11부터
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    t1.addCallback(object : Toast.Callback() {
                        //토스트 메세지가 나타날 때 동작
                        override fun onToastShown() {
                            super.onToastShown()
                            textView.text = "기본 토스트 메시지가 나타남"
                        }

                        //토스트 메세지가 사라질 때
                        override fun onToastHidden() {
                            super.onToastHidden()
                            textView.text = "기본 토스트 메세지가 사라짐"
                        }
                    })
                }
            }

            button2.setOnClickListener {
                //viewBinding 객체 생성
                val toastBinding = ToastBinding.inflate(layoutInflater)

                toastBinding.run {
                    imageViewToast.setImageResource(R.drawable.img_android)
                    textViewToast.text = "CustomToast입니당"

                }

                val t2 = Toast(this@MainActivity)
                //view설정
                t2.view = toastBinding.root

                //배경 - 안드로이드에서 제공
                root.setBackgroundResource(android.R.drawable.toast_frame)

                //위치 설정 - 중앙에서 y방향으로 +300
                t2.setGravity(Gravity.CENTER, 0, 300)
                //시간 설정
                t2.duration = Toast.LENGTH_LONG
                t2.show()

            }

            button3.setOnClickListener {
                //SnackBar객체 생성
                //첫번 째 매개변수 : 스낵바를 관리하는 뷰(아무거나 넣어도됨)
//                snackBar1 = Snackbar.make(it, "기본 스낵바", Snackbar.LENGTH_SHORT)
//                snackBar1 = Snackbar.make(it, "기본 스낵바", Snackbar.LENGTH_LONG)
                //없어지는 시점을 개발자가 설정하고 싶은 경우
                snackBar1 = Snackbar.make(it, "기본 스낵바", Snackbar.LENGTH_INDEFINITE)
                //snackbar의 콜백
                snackBar1.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    //사라질 때
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        textView.text = "SnackBar가 사라짐"
                    }

                    //나타날 때
                    override fun onShown(transientBottomBar: Snackbar?) {
                        super.onShown(transientBottomBar)
                        textView.text = "SnackBar가 나타남"
                    }
                })
                //메시지 색상
                snackBar1.setTextColor(Color.RED)
                snackBar1.setBackgroundTint(Color.GRAY)
                snackBar1.animationMode = Snackbar.ANIMATION_MODE_SLIDE

                //Action설정 - 스낵바 우측에 나옴
                snackBar1.setAction("Action1") {
                    textView2.text = "Action1을 누름"
                }

                snackBar1.show()

            }

            //스낵바 닫아주기
            button4.setOnClickListener {
                if(::snackBar1.isInitialized) {
                    //현재 스낵바가 보여지고 있는 상태라면
                    if(snackBar1.isShown) snackBar1.dismiss()
                }

            }

            button5.setOnClickListener {
                //스낵바 생성
                val snackBar2 = Snackbar.make(it,"custom snackBar", Snackbar.LENGTH_SHORT)
                //viewBinding
                val snackBarBinding = SnackbarBinding.inflate(layoutInflater)

                //view설정
                snackBarBinding.run {
                    imageViewSnackBar.setImageResource(R.drawable.img_android)
                    textViewSnackBar.text = "새로 추가된 뷰"
                    textViewSnackBar.setTextColor(Color.WHITE)
                }

                //snackbar의 레이아웃을 추출하여 새로운 뷰를 추가한다
                val snackBarLayout = snackBar2.view as Snackbar.SnackbarLayout
                snackBarLayout.addView(snackBarBinding.root)

                //snackbar가 가지고있는 textview를 보이지 않게 하기
                //google이 넣어논 id가져와서 invisible로 바까줌
                val t1 = snackBarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                t1.visibility = View.INVISIBLE

                snackBar2.show()
            }
        }
    }
}