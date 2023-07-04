package com.test.android71_viewex01

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android71_viewex01.databinding.ActivityMainBinding
import com.test.android71_viewex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    val dataList = mutableListOf<Person>()
    private lateinit var activityMainBinding: ActivityMainBinding

    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        replaceFragment(FragmentName.FRAGMENT_MAIN, false, false)
    }

    //지정한 fragment를 보여주는 메서드
    fun replaceFragment(name: FragmentName, addToBackStack: Boolean, animate: Boolean) {
        //Fragment 교체 상태로 설정
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //새로운 fragment 담을 변수
        var newFragment: Fragment? = null

        //이름으로 분기
        newFragment = when (name) {
            FragmentName.FRAGMENT_MAIN -> {
                //fragment 객체를 생성한다
                MainFragment()
            }

            FragmentName.FRAGMENT_INPUT -> {
                InputFragment()

            }

            FragmentName.FRAGMENT_RESULT -> {
                ResultFragment()
            }
        }

        //mainFragment가 보여지도록 fragment를 교체한다.
        fragmentTransaction.replace(R.id.fragmentContainerViewMain, newFragment)

        if (animate) {
            //애니메이션 설정
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        if (addToBackStack) {
            //fragment를 backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도로 함
            //이름도 넣어줘서 뺄때 해당 이름으로 뺄 수 있도록
            fragmentTransaction.addToBackStack(name.str)
        }

        //교체명령 동작
        fragmentTransaction.commit()
    }

    //fragment를 backstack에서 제거하는 메서드
    fun removeFragment(name: FragmentName) {
        //즉시 제거하라는 의미
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    //fragment를 구분하기 위한 이름
    enum class FragmentName(val str: String) {
        FRAGMENT_MAIN("MainFragment"),
        FRAGMENT_INPUT("InputFragment"),
        FRAGMENT_RESULT("ResultFragment")

    }

}