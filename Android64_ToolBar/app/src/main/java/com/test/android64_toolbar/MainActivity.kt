package com.test.android64_toolbar

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android64_toolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            toolbar.run {
                //타이틀
                title = "ToolBar"
                //타이틀 글자색
                setTitleTextColor(Color.WHITE)
                //옵션 메뉴 구성(mainActivity의 함수로 오버라이드 하는게 아니라 툴바 아래 넣어줌)
                inflateMenu(R.menu.main_menu)
                //상단 메뉴를 눌러주면 동작하는 리스너
                setOnMenuItemClickListener {
                    // 사용자가 누른 메뉴의 id로 분기한다.
                    when (it?.itemId) {
                        R.id.item1 -> {
                            textView.text = "메뉴1을 눌렀습니다"
                        }

                        R.id.item2 -> {
                            textView.text = "메뉴2를 눌렀습니다"
                        }
                    }
                    false
                }
            }

            button.setOnClickListener {
                val newIntent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(newIntent)
            }
        }
    }

}