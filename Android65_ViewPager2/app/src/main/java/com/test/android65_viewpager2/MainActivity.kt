package com.test.android65_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.test.android65_viewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    //사용할 프래그먼트들
    val fragment1 = Sub1Fragment()
    val fragment2 = Sub2Fragment()
    val fragment3 = Sub3Fragment()
    val fragment4 = Sub4Fragment()
    val fragment5 = Sub5Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            pager2.run {
                adapter = MainFragmentStateAdapter(this@MainActivity)

                //스크롤 방향 반대로
//                layoutDirection = ViewPager2.LAYOUT_DIRECTION_RTL

                //스크롤 방향을 상하로
//                orientation = ViewPager2.ORIENTATION_VERTICAL

                //페이지가 변경될 때
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    //position: 현재 보여진 페이지의 번호
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        textView7.text = "pageSelected: $position"
                    }
                })
            }
        }
    }

    //Viewpager2의 어댑터
    inner class MainFragmentStateAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        //보여줄 페이지 수
        override fun getItemCount(): Int {
            return 5
        }

        //position번째 프래그먼트 반환해서 보여줌
        override fun createFragment(position: Int): Fragment {
            val resultFragment = when (position) {
                0 -> fragment1
                1 -> fragment2
                2 -> fragment3
                3 -> fragment4
                4 -> fragment5
                else -> fragment1
            }

            return resultFragment
        }

    }
}