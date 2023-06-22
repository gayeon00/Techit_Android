package com.test.android47_homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android47_homework.category.AddCategoryActivity
import com.test.android47_homework.category.EditCategoryActivity
import com.test.android47_homework.databinding.ActivityMainBinding
import com.test.android47_homework.databinding.RowCategoryBinding
import com.test.android47_homework.memo.MemoMainActivity

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

//    val categoryList = mutableListOf<Category>()

    lateinit var mainActivityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var editCategoryActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            recyclerView.run {
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

        val c1 = ActivityResultContracts.StartActivityForResult()
        mainActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                //recyclerView adapter업데이트
                activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
        }

        val contractModify = ActivityResultContracts.StartActivityForResult()
        editCategoryActivityResultLauncher = registerForActivityResult(contractModify) {
            if (it.resultCode == RESULT_OK) {
                activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    //옵션 메뉴 만들기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //옵션 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add_category -> {
                val addIntent = Intent(this, AddCategoryActivity::class.java)
                //나중에 result받아오는 걸로 바꾸기
                mainActivityResultLauncher.launch(addIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class MainRecyclerViewAdapter : Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {
        inner class MainRecyclerViewHolder(rowCategoryBinding: RowCategoryBinding) :
            ViewHolder(rowCategoryBinding.root) {
            val textViewCategoryTitle = rowCategoryBinding.textViewRowCategoryName

            init {
                rowCategoryBinding.root.setOnCreateContextMenuListener { contextMenu, view, contextMenuInfo ->
                    contextMenu?.setHeaderTitle(Data.categoryList[adapterPosition].title)
                    menuInflater.inflate(R.menu.row_category_menu, contextMenu)

                    //카테고리 수정 버튼
                    contextMenu[0].setOnMenuItemClickListener {
                        val modifyIntent =
                            Intent(this@MainActivity, EditCategoryActivity::class.java)
                        modifyIntent.putExtra("categoryPosition", this.adapterPosition)
                        modifyIntent.putExtra(
                            "categoryTitle",
                            Data.categoryList[this.adapterPosition].title
                        )
                        editCategoryActivityResultLauncher.launch(modifyIntent)

                        true
                    }
                    //카테고리 삭제 버튼
                    contextMenu[1].setOnMenuItemClickListener {
                        Data.categoryList.removeAt(adapterPosition)

                        this@MainRecyclerViewAdapter.notifyDataSetChanged()
                        true
                    }
                }

                rowCategoryBinding.root.setOnClickListener {
                    //메모 메인으로 가기
                    val memoMainIntent = Intent(this@MainActivity, MemoMainActivity::class.java)
                    memoMainIntent.putExtra("position", this.adapterPosition)
                    startActivity(memoMainIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
            val rowCategoryBinding = RowCategoryBinding.inflate(layoutInflater)
            val viewHolder = MainRecyclerViewHolder(rowCategoryBinding)

            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )

            rowCategoryBinding.root.layoutParams = params

            return viewHolder
        }

        override fun getItemCount(): Int {
            return Data.categoryList.size
        }

        override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
            holder.textViewCategoryTitle.text = Data.categoryList[position].title
        }
    }
}