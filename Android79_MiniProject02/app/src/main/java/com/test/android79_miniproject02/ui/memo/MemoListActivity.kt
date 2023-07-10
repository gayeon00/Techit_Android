package com.test.android79_miniproject02.ui.memo

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.dao.MemoDao
import com.test.android79_miniproject02.data.Memo
import com.test.android79_miniproject02.databinding.ActivityMemoListBinding
import com.test.android79_miniproject02.databinding.RowMemoListBinding

class MemoListActivity : AppCompatActivity() {
    lateinit var activityMemoListBinding: ActivityMemoListBinding

    var categoryId = -1
    var categoryName = ""

    var memoList = listOf<Memo>()
    var memo = Memo(-1,"","","")

    companion object {
        // Activity가 관리할 프래그먼트들의 이름
        const val MAIN_MEMO_FRAGMENT = "MainMemoFragment"
        const val ADD_MEMO_FRAGMENT = "AddMemoFragment"
        const val EDIT_MEMO_FRAGMENT = "EditMemoFragment"
        const val SHOW_MEMO_FRAGMENT = "ShowMemoFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMemoListBinding = ActivityMemoListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMemoListBinding.root)

        categoryId = intent.getIntExtra("categoryId", -1)
        categoryName = intent.getStringExtra("categoryName").toString()

        replaceFragment(MAIN_MEMO_FRAGMENT, false, false)
    }

    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean) {
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        val newFragment = when (name) {
            MAIN_MEMO_FRAGMENT -> {
                MainMemoFragment()
            }

            ADD_MEMO_FRAGMENT -> {
                AddMemoFragment()
            }

            EDIT_MEMO_FRAGMENT -> {
                EditMemoFragment()
            }

            SHOW_MEMO_FRAGMENT -> {
                ShowMemoFragment()
            }

            else -> {
                Fragment()
            }
        }

        // Fragment를 교채한다.
        fragmentTransaction.replace(R.id.memoListContainer, newFragment)

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