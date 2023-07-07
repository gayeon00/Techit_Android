package com.test.android79_miniproject02.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.dao.CategoryDao
import com.test.android79_miniproject02.data.Category
import com.test.android79_miniproject02.data.Memo
import com.test.android79_miniproject02.databinding.ActivityCategoryListBinding
import com.test.android79_miniproject02.databinding.DialogAddCategoryBinding
import com.test.android79_miniproject02.databinding.RowCategoryListBinding

class CategoryListActivity : AppCompatActivity() {
    lateinit var activityCategoryListBinding: ActivityCategoryListBinding
    lateinit var categoryDao: CategoryDao

    var categoryList = listOf<Category>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activityCategoryListBinding = ActivityCategoryListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityCategoryListBinding.root)

        categoryDao = CategoryDao(this)
        categoryList = categoryDao.getAllCategories()
        Log.d("myProject", categoryList.toString())

        activityCategoryListBinding.run {
            toolbarPwSetting.run {
                setOnMenuItemClickListener {
                    //다이얼로그를 통해 카테고리 이름 추가
                    addCategoryWithDialog()
                    false
                }
            }

            recyclerViewCategoryList.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@CategoryListActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        this@CategoryListActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
    }

    private fun addCategoryWithDialog() {
        val dialogAddCategoryBinding = DialogAddCategoryBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("카테고리 추가")
        builder.setIcon(R.mipmap.ic_launcher)

        builder.setView(dialogAddCategoryBinding.root)

        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
            val categoryTitle = dialogAddCategoryBinding.editTextCategoryTitle.text.toString()

            categoryDao.addCategory(categoryTitle)
            categoryList = categoryDao.getAllCategories()
        }

        builder.setNegativeButton("취소", null)
        builder.show()
    }

    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowCategoryListBinding): RecyclerView.ViewHolder(rowBinding.root) {
            val textViewRowCategoryTitle = rowBinding.textViewRowCategoryTitle

            init {
                rowBinding.root.setOnClickListener {
                    //TODO 클릭 시
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowBinding = RowCategoryListBinding.inflate(layoutInflater)
            val recyclerViewHolder = RecyclerViewHolder(rowBinding)

            rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return recyclerViewHolder
        }

        override fun getItemCount(): Int {
            return categoryList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewRowCategoryTitle.text = categoryList[position].name
        }
    }
}