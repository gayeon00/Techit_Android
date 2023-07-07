package com.test.android79_miniproject02.ui.memo

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android79_miniproject02.dao.MemoDao
import com.test.android79_miniproject02.data.Memo
import com.test.android79_miniproject02.databinding.ActivityMemoListBinding
import com.test.android79_miniproject02.databinding.RowMemoListBinding

class MemoListActivity : AppCompatActivity() {
    lateinit var activityMemoListBinding: ActivityMemoListBinding

    private var categoryId = -1
    private var categoryName = ""

    private var memoList = listOf<Memo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMemoListBinding = ActivityMemoListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMemoListBinding.root)

        categoryId = intent.getIntExtra("categoryId", -1)
        categoryName = intent.getStringExtra("categoryName").toString()

        memoList = MemoDao.getMemosByCategoryId(this, categoryId)

        activityMemoListBinding.run {
            toolbarPwSetting.run {
                title = categoryName

            }

            recyclerViewMemoList.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MemoListActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        this@MemoListActivity, DividerItemDecoration.VERTICAL
                    )
                )
                addItemDecoration(CustomItemDecorator(53))
            }
        }
    }

    inner class RecyclerViewAdapter :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowMemoListBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            val textViewRowMemoTitle = rowBinding.textViewRowMemoTitle
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
            return memoList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewRowMemoTitle.text = memoList[position].title
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