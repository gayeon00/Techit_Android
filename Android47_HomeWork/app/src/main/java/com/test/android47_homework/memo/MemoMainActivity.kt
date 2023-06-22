package com.test.android47_homework.memo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android47_homework.Category
import com.test.android47_homework.Data
import com.test.android47_homework.Memo
import com.test.android47_homework.R
import com.test.android47_homework.databinding.ActivityMainMemoBinding
import com.test.android47_homework.databinding.RowMemoBinding

//여기서 뒤로가기를 해버리면 activity가 죽어버려서 memolist가 초기화됨!! -> 어떻게? back버튼 누르면 이전 액티비티로 intent보내게
class MemoMainActivity : AppCompatActivity() {
    lateinit var activityMemoMainBinding: ActivityMainMemoBinding
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    var position = 0
    var memoList = mutableListOf<Memo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMemoMainBinding = ActivityMainMemoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMemoMainBinding.root)

        position = intent.getIntExtra("position",0)
        supportActionBar?.title = Data.categoryList[position].title

        //카테고리가 가지고있는 memoList
        memoList = Data.categoryList[position].memoList


        activityMemoMainBinding.run {
            recyclerViewMemo.run {
                adapter = MemoRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MemoMainActivity)
            }
        }

        val addMemoContracts = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(addMemoContracts) {
            if (it.resultCode == RESULT_OK) {
                //recyclerView adapter업데이트
                activityMemoMainBinding.recyclerViewMemo.adapter?.notifyDataSetChanged()
            }
        }

    }

    //옵션 메뉴 만들기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_memo_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //옵션 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add_memo -> {
                val addIntent = Intent(this, MemoAddActivity::class.java)
                addIntent.putExtra("position", position)
                addActivityResultLauncher.launch(addIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class MemoRecyclerViewAdapter :
        Adapter<MemoRecyclerViewAdapter.MemoRecyclerViewHolder>() {
        inner class MemoRecyclerViewHolder(rowMemoBinding: RowMemoBinding) :
            ViewHolder(rowMemoBinding.root) {
            val textViewMemoName = rowMemoBinding.textViewMemoName
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoRecyclerViewHolder {
            val rowMemoBinding = RowMemoBinding.inflate(layoutInflater)
            val memoViewHolder = MemoRecyclerViewHolder(rowMemoBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            rowMemoBinding.root.layoutParams = params

            return memoViewHolder

        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: MemoRecyclerViewHolder, position: Int) {
            holder.textViewMemoName.text = memoList[position].title
        }
    }
}

