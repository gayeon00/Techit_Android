package com.test.android64_toolbar

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android64_toolbar.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var activitySecondBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.run {
            toolbar2.run {
                title = "SecondActivity"
                setTitleTextColor(Color.WHITE)

                //백버튼 아이콘 표시
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                // 백 버튼의 아이콘 색상을 변경한다.
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    //투명색이 아닌 부분을 흰색으로 채워주기
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    finish()
                }

            }
        }
    }
}