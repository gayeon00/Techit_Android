package com.test.android74_filesystemex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android74_filesystemex01.databinding.ActivityMainBinding
import com.test.android74_filesystemex01.databinding.RowBinding
import java.io.EOFException
import java.io.ObjectInputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val dataList = mutableListOf<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        readPersonListFromFile()

        activityMainBinding.run {
            recyclerView.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)

                addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }

            toolbar.run {
                setOnMenuItemClickListener {
                    if (it.itemId == R.id.action_add) {
                        //추가 하는 화면으로
                        val addIntent = Intent(this@MainActivity, AddActivity::class.java)
                        startActivity(addIntent)
                    }
                    false
                }
            }
        }


    }

    private fun readPersonListFromFile() {
        try{
            val fis = openFileInput("data1.dat")

            while (true) {
                try {
                    //쓸 때도 하나의 스트림으로 해준게 아니라 받을 때도 매번 새로운 스트림으로 해주어야함
                    val ois = ObjectInputStream(fis)
                    val person = ois.readObject() as Person
                    dataList.add(person)
                } catch (e: EOFException) {
                    break
                }
            }
            fis.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    inner class RecyclerViewAdapter : Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root) {
            val textViewRowName = rowBinding.textViewRowName

            init {
                rowBinding.root.setOnClickListener {
                    val showIntent = Intent(this@MainActivity, ShowActivity::class.java)
                    showIntent.putExtra("person", dataList[adapterPosition])
                    startActivity(showIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            rowBinding.root.layoutParams = params
            return RecyclerViewHolder(rowBinding)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewRowName.text = dataList[position].name
        }
    }
}