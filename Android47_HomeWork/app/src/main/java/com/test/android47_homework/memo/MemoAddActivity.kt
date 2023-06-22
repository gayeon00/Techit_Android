package com.test.android47_homework.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android47_homework.Category
import com.test.android47_homework.Data
import com.test.android47_homework.Memo
import com.test.android47_homework.R
import com.test.android47_homework.databinding.ActivityMemoAddBinding

class MemoAddActivity : AppCompatActivity() {
    lateinit var activityMemoAddBinding: ActivityMemoAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMemoAddBinding = ActivityMemoAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMemoAddBinding.root)

        supportActionBar?.title = "메모 추가"

        val position = intent.getIntExtra("position", 0)
        activityMemoAddBinding.run {

            buttonAddMemoComplete.setOnClickListener {
                val addIntent = Intent()
                Data.categoryList[position].memoList.add(Memo(editTextAddMemoTitle.text.toString(), editTextAddMemoContent.text.toString()))
                setResult(RESULT_OK, addIntent)
                finish()
            }

            buttonAddMemoCancel.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }
}