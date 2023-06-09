package com.test.android47_homework.category

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android47_homework.Data
import com.test.android47_homework.databinding.ActivityEditCategoryBinding

class EditCategoryActivity : AppCompatActivity() {
    lateinit var activityEditCategoryBinding: ActivityEditCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityEditCategoryBinding = ActivityEditCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityEditCategoryBinding.root)

        supportActionBar?.title = "카테고리 수정"

        val title = intent.getStringExtra("categoryTitle")
        activityEditCategoryBinding.run {
            textViewEditCategoryName.text = title

            buttonEditComplete.setOnClickListener {
                val editIntent = Intent()
                val position = intent.getIntExtra("categoryPosition",0)
                val newCategoryTitle = editTextEditCategoryName.text.toString()
                Data.categoryList[position].title = newCategoryTitle
                setResult(RESULT_OK, editIntent)
                finish()
            }

            buttonEditCancel.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }

    }
}