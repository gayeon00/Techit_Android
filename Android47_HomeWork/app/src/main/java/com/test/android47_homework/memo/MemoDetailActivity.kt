package com.test.android47_homework.memo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android47_homework.Data
import com.test.android47_homework.R
import com.test.android47_homework.databinding.ActivityMemoDetailBinding

class MemoDetailActivity : AppCompatActivity() {
    lateinit var activityMemoDetailBinding: ActivityMemoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMemoDetailBinding = ActivityMemoDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMemoDetailBinding.root)
        supportActionBar?.hide()

        activityMemoDetailBinding.run {
            val categoryPosition = intent.getIntExtra("categoryPosition", 0)
            val memoPosition = intent.getIntExtra("memoPosition", 0)
            val memo = Data.categoryList[categoryPosition].memoList[memoPosition]
            textViewMemoDetailTitle.text = memo.title
            textViewMemoDetailContent.text = memo.content
        }
    }
}