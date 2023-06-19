package com.test.android45_ex01

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android45_ex01.databinding.ActivityMainBinding
import com.test.android45_ex01.databinding.RowBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    val dataList = mutableListOf<DataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //다른 액티비티로 갔다가 돌아올 때 result 받아오기
        val c1 = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(c1) {

            if(it.resultCode == RESULT_OK){
                val data1 = it.data?.getStringExtra("data1")
                val data2 = it.data?.getStringExtra("data2")

                val t1 = DataClass(data1!!, data2!!)
                dataList.add(t1)

                activityMainBinding.recyclerViewResult.adapter?.notifyDataSetChanged()
            }
        }

        activityMainBinding.run {
            recyclerViewResult.run {
                adapter = RecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.main_menu_add -> {
                val addIntent = Intent(this, AddActivity::class.java)

                //다른 액티비티 실행할 때, 거기서 부터 받아올게 있어서 resultlauncher사용
                addActivityResultLauncher.launch(addIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    data class DataClass(var data1: String, val data2: String)

    inner class RecyclerAdapter: Adapter<RecyclerAdapter.RecyclerViewHolder>() {

        inner class RecyclerViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root) {
            val textViewRow1= rowBinding.textViewRow1
            var textViewRow2= rowBinding.textViewRow2

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
            return dataList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewRow1.text = "data1 : ${dataList[position].data1}"
            holder.textViewRow2.text = "data2 : ${dataList[position].data2}"
        }


    }
}