package com.test.android79_miniproject02.ui.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.dao.MemoDao
import com.test.android79_miniproject02.data.Memo
import com.test.android79_miniproject02.databinding.FragmentEditMemoBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditMemoFragment : Fragment() {
    lateinit var fragmentEditMemoBinding: FragmentEditMemoBinding
    lateinit var memoListActivity: MemoListActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentEditMemoBinding = FragmentEditMemoBinding.inflate(layoutInflater)
        memoListActivity = activity as MemoListActivity

        fragmentEditMemoBinding.run {
            toolbarPwSetting.run {
                setOnMenuItemClickListener {
                    val newTitle = editTextMemoTitle2.text.toString()
                    val newContent = editTextMemoContent2.text.toString()

                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    //현재시간 갖고있는 데이터
                    val now = sdf.format(Date())

                    val newMemo = Memo(memoListActivity.categoryId, newTitle, newContent, now)
                    MemoDao.updateMemo(memoListActivity, memoListActivity.memo, newMemo)
                    memoListActivity.removeFragment(MemoListActivity.SHOW_MEMO_FRAGMENT)

                    false
                }

                setNavigationOnClickListener {
                    memoListActivity.removeFragment(MemoListActivity.SHOW_MEMO_FRAGMENT)
                }
            }

        }
        // Inflate the layout for this fragment
        return fragmentEditMemoBinding.root
    }

}