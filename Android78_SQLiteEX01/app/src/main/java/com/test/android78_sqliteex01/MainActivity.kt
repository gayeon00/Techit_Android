package com.test.android78_sqliteex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android78_sqliteex01.databinding.ActivityMainBinding
import com.test.android78_sqliteex01.db.DAO
import com.test.android78_sqliteex01.fragment.InputFragment
import com.test.android78_sqliteex01.fragment.MainFragment
import com.test.android78_sqliteex01.fragment.ShowFragment

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    // 사용자가 누른 항목 번호
    var rowPosition = 0

    companion object {
        // Activity가 관리할 프래그먼트들의 이름
        const val MAIN_FRAGMENT = "MainFragment"
        const val INPUT_FRAGMENT = "InputFragment"
        const val SHOW_FRAGMENT = "ShowFragment"

        // 학생 데이터를 저장할 DB 이름
        const val STUDENT_DB_NAME = "StudentTable"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        replaceFragment(MAIN_FRAGMENT, false, false)
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        val newFragment = when(name){
            MAIN_FRAGMENT -> {
                MainFragment()
            }
            INPUT_FRAGMENT -> {
                InputFragment()
            }
            SHOW_FRAGMENT -> {
                ShowFragment()
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
    fun removeFragment(name:String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    //학생 추가
    fun insertStudent(student: Student){
        DAO.insertData(this, student)
    }

    // 학생 데이터를 읽어오는 메서드
    fun getAllStudent() : MutableList<Student> {
        return DAO.selectAllData(this)
    }

    //특정 학생 읽어오기
    fun getStudent(index: Int): Student {
        return DAO.selectData(this, index)
    }

}
// 정보를 담을 객체
data class Student(var name:String, var age:Int, var korean:Int)
