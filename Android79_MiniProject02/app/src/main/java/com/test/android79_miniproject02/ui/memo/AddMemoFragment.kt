package com.test.android79_miniproject02.ui.memo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.dao.MemoDao
import com.test.android79_miniproject02.data.Memo
import com.test.android79_miniproject02.databinding.FragmentAddMemoBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddMemoFragment : Fragment() {
    lateinit var fragmentAddMemoBinding: FragmentAddMemoBinding
    lateinit var memoListActivity: MemoListActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAddMemoBinding = FragmentAddMemoBinding.inflate(layoutInflater)
        memoListActivity = activity as MemoListActivity
        // Inflate the layout for this fragment

        fragmentAddMemoBinding.run {
            toolbarPwSetting.run {
                setOnMenuItemClickListener {
                    //저장
                    val memoTitle = editTextMemoTitle.text.toString()
                    val memoContent = editTextMemoContent.text.toString()

                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    //현재시간 갖고있는 데이터
                    val now = sdf.format(Date())

                    MemoDao.addMemo(memoListActivity, Memo(memoListActivity.categoryId, memoTitle, memoContent, now))
                    memoListActivity.removeFragment(MemoListActivity.ADD_MEMO_FRAGMENT)
                    false
                }

                setNavigationOnClickListener {
                    memoListActivity.removeFragment(MemoListActivity.ADD_MEMO_FRAGMENT)
                }
            }
        }
        return fragmentAddMemoBinding.root
    }

}