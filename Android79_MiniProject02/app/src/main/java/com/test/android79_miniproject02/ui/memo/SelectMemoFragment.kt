package com.test.android79_miniproject02.ui.memo

import android.graphics.Rect
import android.os.Bundle
import android.util.SparseBooleanArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android79_miniproject02.dao.MemoDao
import com.test.android79_miniproject02.databinding.FragmentSelectMemoBinding
import com.test.android79_miniproject02.databinding.RowSelectMemoBinding

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

                setOnMenuItemClickListener { 
                    //선택한 메모들 삭제하기
                    val adapter = recyclerViewSelectMemoList.adapter as RecyclerViewAdapter
                    adapter.removeSelectedItems()
                    memoListActivity.removeFragment(MemoListActivity.SELECT_MEMO_FRAGMENT)
                    false
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
                addItemDecoration(CustomItemDecorator(53))
            }
        }
        // Inflate the layout for this fragment
        return fragmentSelectMemoBinding.root
    }

    inner class RecyclerViewAdapter :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        private val selectedItems = SparseBooleanArray()
        inner class RecyclerViewHolder(rowBinding: RowSelectMemoBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            val checkBox = rowBinding.checkBox

            init {
                checkBox.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        toggleSelection(position)
                    }
                }
            }

        }

        // 선택된 항목을 selectedItems에 추가 또는 제거하는 메서드
        fun toggleSelection(position: Int) {
            if (selectedItems.get(position, false)) {
                selectedItems.delete(position)
            } else {
                selectedItems.put(position, true)
            }
        }
        
        fun removeSelectedItems() {
            val selectedPositions  = getSelectedPositions().reversed() // 역순으로 제거해야 Index 오류가 발생하지 않습니다.

            for(position in selectedPositions) {
                MemoDao.deleteMemoByDate(memoListActivity,memoListActivity.memoList[position].date)
            }

            selectedItems.clear()
        }

        private fun getSelectedPositions(): List<Int> {
            val positions = mutableListOf<Int>()
            for (i in 0 until selectedItems.size()) {
                positions.add(selectedItems.keyAt(i))
            }
            return positions
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