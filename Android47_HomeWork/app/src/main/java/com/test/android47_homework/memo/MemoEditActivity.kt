package com.test.android47_homework.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android47_homework.Data
import com.test.android47_homework.R
import com.test.android47_homework.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {
    lateinit var activityMemoEditBinding: ActivityMemoEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMemoEditBinding = ActivityMemoEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMemoEditBinding.root)

        val categoryPosition = intent.getIntExtra("categoryPosition", 0)
        val memoPosition = intent.getIntExtra("memoPosition", 0)
        supportActionBar?.title = "메모 수정"

        val memo = Data.categoryList[categoryPosition].memoList[memoPosition]
        activityMemoEditBinding.run {
            textViewEditMemoTitle.text = memo.title
            textViewEditMemoContent.text = memo.content

            buttonEditMemoComplete.setOnClickListener {
                val editIntent = Intent()
                //newTitle, newContent보내기

                val newTitle = editTextEditMemoTitle.text.toString()
                val newContent = editTextEditMemoContent.text.toString()

                memo.title = newTitle
                memo.content = newContent
                setResult(RESULT_OK, editIntent)
                finish()
            }

            buttonEditMemoCancel.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }
}