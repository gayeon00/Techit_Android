package com.test.android65_toolbarex01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android65_toolbarex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val personList = mutableListOf<Person>()
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
        when (name) {
            FragmentName.FRAGMENT_MAIN -> {
                //fragment 객체를 생성한다
                newFragment = MainFragment()
            }

            FragmentName.FRAGMENT_INPUT -> {
                newFragment = InputFragment()

            }

            FragmentName.FRAGMENT_RESULT -> {
                newFragment = ResultFragment()
            }
        }

        if (newFragment != null) {
            //mainFragment가 보여지도록 fragment를 교체한다.
            fragmentTransaction.replace(R.id.fragmentContainerView, newFragment)

            if (animate) {
                //애니메이션 설정
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }

            if (addToBackStack) {
                //fragment를 backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도로 함
                //이름도 넣어줘서 뺄때 해당 이름으로 뺄 수 있도록
                fragmentTransaction.addToBackStack(name.str)
            }

            //교체명령 동작
            fragmentTransaction.commit()
        }
    }

    //fragment를 backstack에서 제거하는 메서드
    fun removeFragment(name: FragmentName) {
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}


//fragment를 구분하기 위한 이름
enum class FragmentName(val str: String) {
    FRAGMENT_MAIN("MainFragment"),
    FRAGMENT_INPUT("InputFragment"),
    FRAGMENT_RESULT("ResultFragment")

}