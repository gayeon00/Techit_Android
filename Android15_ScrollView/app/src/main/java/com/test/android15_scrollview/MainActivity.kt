package com.test.android15_scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnScrollChangeListener
import com.test.android15_scrollview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            //ScrollView의 Scroll이벤트
            scroll1.run {
                setOnScrollChangeListener { view, i, i2, i3, i4 ->
                    textView2.text = "Y : $i4 -> $i2"
                }
            }

            scroll2.run {
                setOnScrollChangeListener { view, i, i2, i3, i4 ->
                    textView2.text = "Y : $i3 -> $i"
                }
            }

            button8.setOnClickListener {
                // ScrollView의 Y 좌표를 가져온다.
                textView.text = "X : ${scroll2.scrollX}"
                textView2.text = "Y : ${scroll1.scrollY}"
            }

            button9.setOnClickListener {
//                // 지정된 위치로 이동한다.
//                scroll1.scrollTo(0, 1000)
//                scroll2.scrollTo(1000, 0)
//                // 현재 위치에서 지정된 만큼 이동한다.
//                scroll1.scrollBy(0, 100)
//                scroll2.scrollBy(100, 0)
                // 지정된 만큼 이동한다.(애니메이션)
                scroll1.smoothScrollBy(0, 100)
                scroll2.smoothScrollBy(100, 0)
            }


        }
    }

    inner class ScrollViewChangeListener1: OnScrollChangeListener{
        override fun onScrollChange(
            p0: View?,
            p1: Int,
            p2: Int,
            p3: Int,
            p4: Int
        ) {

        }
    }
}