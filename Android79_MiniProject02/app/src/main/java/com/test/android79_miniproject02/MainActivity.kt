package com.test.android79_miniproject02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android79_miniproject02.databinding.ActivityMainBinding
import com.test.android79_miniproject02.user.LogInFragment
import com.test.android79_miniproject02.user.SetPasswordFragment


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    companion object {
        // Activity가 관리할 프래그먼트들의 이름
        const val LOGIN_FRAGMENT = "LogInFragment"
        const val SET_PASSWORD_FRAGMENT = "SetPasswordFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        if (checkPwdSetting()) {
            replaceFragment(LOGIN_FRAGMENT, false, false)
        } else {
            replaceFragment(SET_PASSWORD_FRAGMENT, false, false)
        }
    }

    private fun checkPwdSetting(): Boolean {
        val pref = getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)

        return pref.getString("password", "") != ""
    }

    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean) {
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        val newFragment = when (name) {
            LOGIN_FRAGMENT -> {
                LogInFragment()
            }

            SET_PASSWORD_FRAGMENT -> {
                SetPasswordFragment()
            }

            else -> {
                Fragment()
            }
        }

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