package com.test.android83_preferencescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android83_preferencescreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    companion object {
        const val SETTING_FRAGMENT = "SettingFragment"
        const val RESULT_FRAGMENT = "ResultFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                replaceFragment(SETTING_FRAGMENT, false, false, null)
            }

            button2.setOnClickListener {
                replaceFragment(RESULT_FRAGMENT, false, false, null)
            }
        }

        replaceFragment(SETTING_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean, bundle: Bundle?) {
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        val newFragment = when (name) {
            SETTING_FRAGMENT -> {
                SettingFragment()
            }

            RESULT_FRAGMENT -> {
                ResultFragment()
            }

            else -> {
                Fragment()
            }
        }

        //mainActivity의 변수를 갖다 쓰는 방법 대신, bundle로 프래그먼트에 필요한 데이터 붙여주는 방법!
        newFragment.arguments = bundle

        // Fragment를 교채한다.
        fragmentTransaction.replace(R.id.mainContainer, newFragment)

        if (animate) {
            // 애니메이션을 설정한다.
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        if (addToBackStack) {
            // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
            fragmentTransaction.addToBackStack(name)
        }

        // 교체 명령이 동작하도록 한다.
        fragmentTransaction.commit()
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}