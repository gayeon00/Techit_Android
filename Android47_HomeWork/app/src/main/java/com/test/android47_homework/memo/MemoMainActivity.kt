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
import com.test.android47_homework.Memo
import com.test.android47_homework.R
import com.test.android47_homework.databinding.ActivityMainMemoBinding
import com.test.android47_homework.databinding.RowMemoBinding

//여기서 뒤로가기를 해버리면 activity가 죽어버려서 memolist가 초기화됨!! -> 어떻게? back버튼 누르면 이전 액티비티로 intent보내게
class MemoMainActivity : AppCompatActivity() {
    lateinit var activityMemoMainBinding: ActivityMainMemoBinding
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    var memoList = mutableListOf<Memo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMemoMainBinding = ActivityMainMemoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMemoMainBinding.root)

        // 이전에 저장된 Bundle이 있는 경우 해당 Memo 리스트를 복원합니다.
        if (savedInstanceState != null) {
            memoList = savedInstanceState.getParcelableArrayList(KEY_MEMO_LIST) ?: mutableListOf()
        }

        val category = intent.getParcelableExtra<Category>("category")
        if (category != null) {
            supportActionBar?.title = category.title

            // 이전에 저장된 Bundle이 있는 경우 해당 Memo 리스트를 복원합니다.
            memoList = if (savedInstanceState != null) {
                savedInstanceState.getParcelableArrayList(KEY_MEMO_LIST) ?: mutableListOf()
            } else {
                //category로부터
                category.memoList
            }

        }

        activityMemoMainBinding.run {
            recyclerViewMemo.run {
                adapter = MemoRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MemoMainActivity)
            }
        }

        val addMemoContracts = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(addMemoContracts) {
            if (it.resultCode == RESULT_OK) {
                //new memo category에 추가, recyclerview adapter 업데이트
                val memo = it.data?.getParcelableExtra<Memo>("memo")

                if (memo != null) {
                    memoList.add(memo)
                }
                //recyclerView adapter업데이트
                activityMemoMainBinding.recyclerViewMemo.adapter?.notifyDataSetChanged()

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("lion", "onSaveInstanceState$outState.")
        // Memo 리스트를 Bundle에 추가합니다.
        outState.putParcelableArrayList(KEY_MEMO_LIST, ArrayList(memoList))
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
                //
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

    companion object {
        private const val KEY_MEMO_LIST = "memoList"
    }
}

