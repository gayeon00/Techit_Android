package com.test.android79_miniproject02.ui.memo

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.dao.MemoDao
import com.test.android79_miniproject02.data.Memo
import com.test.android79_miniproject02.databinding.ActivityMemoListBinding
import com.test.android79_miniproject02.databinding.FragmentMainMemoBinding
import com.test.android79_miniproject02.databinding.RowMemoListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMemoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMemoFragment : Fragment() {
    lateinit var fragmentMainMemoBinding: FragmentMainMemoBinding
    lateinit var memoListActivity: MemoListActivity
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainMemoBinding = FragmentMainMemoBinding.inflate(layoutInflater)
        memoListActivity = activity as MemoListActivity

        memoListActivity.memoList = MemoDao.getMemosByCategoryId(memoListActivity, memoListActivity.categoryId)

        fragmentMainMemoBinding.run {
            toolbarPwSetting.run {
                title = memoListActivity.categoryName

                //뒤로가기
                setNavigationOnClickListener {
                    memoListActivity.finish()
                }

                setOnMenuItemClickListener {
                    //메모 추가
                    memoListActivity.replaceFragment(MemoListActivity.ADD_MEMO_FRAGMENT, true, true)
                    false
                }
            }

            recyclerViewMemoList.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(memoListActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        memoListActivity, DividerItemDecoration.VERTICAL
                    )
                )
                addItemDecoration(CustomItemDecorator(53))
            }
        }
        
        return fragmentMainMemoBinding.root
    }

    inner class RecyclerViewAdapter :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowMemoListBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            val textViewRowMemoTitle = rowBinding.textViewRowMemoTitle

            init {
                rowBinding.root.setOnClickListener {
                    memoListActivity.memo = memoListActivity.memoList[adapterPosition]
                    memoListActivity.replaceFragment(MemoListActivity.SHOW_MEMO_FRAGMENT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowBinding = RowMemoListBinding.inflate(layoutInflater)
            val recyclerViewHolder = RecyclerViewHolder(rowBinding)

            rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return recyclerViewHolder
        }

        override fun getItemCount(): Int {
            return memoListActivity.memoList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewRowMemoTitle.text = memoListActivity.memoList[position].title
        }
    }

    class CustomItemDecorator(private val desiredHeightInPixels: Int) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.set(0, desiredHeightInPixels / 2, 0, desiredHeightInPixels / 2)
        }
    }

}