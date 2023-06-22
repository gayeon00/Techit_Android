package com.test.android46_ex02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android46_ex02.databinding.ActivityMainBinding
import com.test.android46_ex02.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    val fruitList = mutableListOf<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        val c1 = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                val fruit = it.data?.getParcelableExtra<Fruit>("fruit")

                if (fruit != null) {
                    fruitList.add(fruit)

                    activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }

        activityMainBinding.run {
            recyclerView.adapter = RecyclerAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_add -> {
                val addIntent = Intent(this, AddActivity::class.java)

                //다른 액티비티 실행할 때, 거기서 부터 받아올게 있어서 resultlauncher사용
                addActivityResultLauncher.launch(addIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        val adapter = activityMainBinding.recyclerView.adapter as RecyclerAdapter
        adapter.notifyDataSetChanged()
    }

    inner class RecyclerAdapter: Adapter<RecyclerAdapter.RecyclerViewHolder>() {

        inner class RecyclerViewHolder(rowBinding: RowBinding): ViewHolder(rowBinding.root) {
            val textViewFruitName = rowBinding.textViewFruitName

            init {
                rowBinding.root.setOnClickListener {
                    //디테일 화면으로 넘어가기(Intent, 데이터 실어 보내기)
                    val myIntent = Intent(this@MainActivity, FruitDetailActivity::class.java)
                    myIntent.putExtra("fruit", fruitList[adapterPosition])
                    startActivity(myIntent)
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowBinding = RowBinding.inflate(layoutInflater)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            rowBinding.root.layoutParams = params

            return RecyclerViewHolder(rowBinding)
        }

        override fun getItemCount(): Int {
            return fruitList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewFruitName.text = fruitList[position].name
        }
    }
}

//TODO: 리사이클러뷰에서 과일 이름 누르면