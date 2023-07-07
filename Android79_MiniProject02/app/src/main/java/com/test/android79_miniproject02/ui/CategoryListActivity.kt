package com.test.android79_miniproject02.ui

import android.content.DialogInterface
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.dao.CategoryDao
import com.test.android79_miniproject02.data.Category
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
        updateCategoryList()

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
                addItemDecoration(CustomItemDecorator(53))
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

            //존재 한다면
            if (categoryDao.selectCategory(categoryTitle) != null) {
                Toast.makeText(this, "이미 존재하는 카테고리입니다.", Toast.LENGTH_SHORT).show()
            } else {
                categoryDao.addCategory(categoryTitle)
                updateCategoryList()
            }
        }

        builder.setNegativeButton("취소", null)
        builder.show()
    }

    inner class RecyclerViewAdapter :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowCategoryListBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            val textViewRowCategoryTitle = rowBinding.textViewRowCategoryTitle

            init {
                rowBinding.root.setOnClickListener {
                    //TODO 클릭 시
                }

                //컨텍스트 메뉴 생성
                rowBinding.root.setOnCreateContextMenuListener { contextMenu, view, contextMenuInfo ->
                    menuInflater.inflate(R.menu.context_category_list, contextMenu)

                    //카테고리 수정
                    contextMenu[0].setOnMenuItemClickListener {
                        editCategoryWithDialog()

                        false
                    }

                    //카테고리 삭제
                    contextMenu[1].setOnMenuItemClickListener {
                        deleteCategory()

                        false
                    }
                }
            }

            private fun deleteCategory() {
                Log.d("delete", "deleteCategory 메서드")
                categoryDao.deleteCategory(categoryList[adapterPosition].name)
                updateCategoryList()
            }

            private fun editCategoryWithDialog() {
                val dialogEditCategoryBinding = DialogAddCategoryBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder(this@CategoryListActivity)
                builder.setTitle("카테고리 수정")
                builder.setIcon(R.mipmap.ic_launcher)

                builder.setView(dialogEditCategoryBinding.root)

                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                    val newCategoryTitle =
                        dialogEditCategoryBinding.editTextCategoryTitle.text.toString()
                    val oldCategoryTitle = categoryList[adapterPosition].name

                    if (categoryDao.selectCategory(newCategoryTitle) != null) {
                        Toast.makeText(this@CategoryListActivity, "이미 존재하는 카테고리입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        //카테고리 이름으로 찾아서 수정
                        categoryDao.updateCategory(oldCategoryTitle, newCategoryTitle)
                        updateCategoryList()
                    }
                }

                builder.setNegativeButton("취소", null)
                builder.show()
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

    private fun updateCategoryList() {
        categoryList = categoryDao.getAllCategories()
        Log.d("categoryListPrint", categoryList.toString())
        activityCategoryListBinding.recyclerViewCategoryList.adapter?.notifyDataSetChanged()
    }

    class CustomItemDecorator(private val desiredHeightInPixels: Int) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(0, desiredHeightInPixels / 2, 0, desiredHeightInPixels / 2)
        }
    }
}