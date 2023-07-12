package com.test.android79_miniproject02.ui.memo

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android79_miniproject02.databinding.FragmentSelectMemoBinding
import com.test.android79_miniproject02.databinding.RowSelectMemoBinding
import com.test.android79_miniproject02.ui.CategoryListActivity

class SelectMemoFragment : Fragment() {
    lateinit var fragmentSelectMemoBinding: FragmentSelectMemoBinding
    lateinit var memoListActivity: MemoListActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSelectMemoBinding = FragmentSelectMemoBinding.inflate(layoutInflater)
        memoListActivity = activity as MemoListActivity

        fragmentSelectMemoBinding.run {
            toolbarSelectMemo.run {
                //뒤로가기
                setNavigationOnClickListener {
                    memoListActivity.removeFragment(MemoListActivity.SELECT_MEMO_FRAGMENT)
                }
            }
            recyclerViewSelectMemoList.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(memoListActivity)

                addItemDecoration(
                    DividerItemDecoration(
                        memoListActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
                addItemDecoration(CategoryListActivity.CustomItemDecorator(53))
            }
        }
        // Inflate the layout for this fragment
        return fragmentSelectMemoBinding.root
    }

    inner class RecyclerViewAdapter :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowSelectMemoBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            val checkBox = rowBinding.checkBox

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowBinding = RowSelectMemoBinding.inflate(layoutInflater)
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
            holder.checkBox.text = memoListActivity.memoList[position].title
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