package com.test.android47_homework.category

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.test.android47_homework.Category
import com.test.android47_homework.R
import com.test.android47_homework.databinding.ActivityAddCategoryBinding

class AddCategoryActivity : AppCompatActivity() {
    lateinit var activityAddCategoryBinding: ActivityAddCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityAddCategoryBinding = ActivityAddCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityAddCategoryBinding.root)

        supportActionBar?.title = "카테고리 추가"

        activityAddCategoryBinding.run {

            buttonAdd.setOnClickListener {
                val addIntent = Intent()
                addIntent.putExtra("categoryTitle", Category(editTextCategoryName.text.toString()))
                setResult(RESULT_OK, addIntent)
                finish()
            }

            buttonCancel.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }
}