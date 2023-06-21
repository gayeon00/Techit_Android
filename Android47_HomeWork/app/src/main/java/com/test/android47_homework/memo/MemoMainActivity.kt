package com.test.android47_homework.memo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android47_homework.Category
import com.test.android47_homework.R

class MemoMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_memo)

        val category = intent.getParcelableExtra<Category>("category")

        if (category != null) {
            supportActionBar?.title = category.title
        }


    }
}