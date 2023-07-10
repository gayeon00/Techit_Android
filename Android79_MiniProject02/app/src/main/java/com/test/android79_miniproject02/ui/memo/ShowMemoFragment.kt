package com.test.android79_miniproject02.ui.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.dao.MemoDao
import com.test.android79_miniproject02.databinding.FragmentShowMemoBinding

class ShowMemoFragment : Fragment() {
    lateinit var fragmentShowMemoBinding: FragmentShowMemoBinding
    lateinit var memoListActivity: MemoListActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentShowMemoBinding = FragmentShowMemoBinding.inflate(layoutInflater)
        memoListActivity = activity as MemoListActivity

        fragmentShowMemoBinding.run {
            textViewMemoTitle.text = memoListActivity.memo.title
            textViewMemoDate.text = memoListActivity.memo.date
            textViewMemoContent.text = memoListActivity.memo.content

            toolbarPwSetting.run {
                setNavigationOnClickListener {
                    memoListActivity.removeFragment(MemoListActivity.SHOW_MEMO_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        //수정 시
                        R.id.action_edit -> {

                        }
                        //삭제 시
                        R.id.action_delete -> {
                            MemoDao.deleteMemoByDate(memoListActivity, memoListActivity.memo.date)
                            memoListActivity.removeFragment(MemoListActivity.SHOW_MEMO_FRAGMENT)
                        }
                    }
                    false
                }
            }
        }
        // Inflate the layout for this fragment
        return fragmentShowMemoBinding.root
    }

}