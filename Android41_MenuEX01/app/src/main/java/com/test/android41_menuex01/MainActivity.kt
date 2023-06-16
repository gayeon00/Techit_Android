package com.test.android41_menuex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android41_menuex01.databinding.ActivityMainBinding
import com.test.android41_menuex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val studentList = mutableListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            recyclerView.run {
                //recyclerview adapter랑 연결해주기
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)

            }

            editTextKorScore.setOnEditorActionListener { textView, i, keyEvent ->
                val student = Student(
                    editTextName.text.toString(),
                    editTextAge.text.toString(),
                    textView.text.toString()
                )
                studentList.add(student)

                editTextName.setText("")
                editTextAge.setText("")
                textView.text = ""

                editTextName.requestFocus()

                recyclerView.adapter?.notifyDataSetChanged()
                false
            }
        }
    }

    //옵션 메뉴 구성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //xml파일로 부터 메뉴 생성
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //옵션 항목 선택하면 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activityMainBinding.run {
            when (item.itemId) {
                R.id.menu_item_register -> {
                    linearLayoutRegister.visibility = View.VISIBLE
                    linearLayoutShow.visibility = View.GONE
                }

                R.id.menu_item_show -> {
                    linearLayoutRegister.visibility = View.GONE
                    linearLayoutShow.visibility = View.VISIBLE
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerViewAdapter : Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        inner class RecyclerViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root) {
            var textViewName = rowBinding.textViewName
            var textViewAge = rowBinding.textViewAge
            var textViewKorScore = rowBinding.textViewKorScore

            init {
                //항목 하나의 view에 컨텍스트 메뉴 생성 이벤트를 붙여줌 (menuInfo는 null이어서 필요하면 만들어줘야함)
                rowBinding.root.setOnCreateContextMenuListener { menu, view, contextMenuInfo ->
                    Log.d("룰루랄라", "OnLongClick")
                    menu?.setHeaderTitle("${studentList[adapterPosition].name}")
                    menuInflater.inflate(R.menu.row_menu, menu)

                    //첫번째 메뉴에 대한 이벤트 처리
                    menu[0].setOnMenuItemClickListener {
                        studentList.removeAt(adapterPosition)

                        this@RecyclerViewAdapter.notifyDataSetChanged()
                        false
                    }
                }
            }

        }

        //viewHolder생성해서 반환
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowBinding = RowBinding.inflate(layoutInflater)

            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params

            return RecyclerViewHolder(rowBinding)
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        //holder랑 데이터랑 바인딩
        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewName.text = studentList[position].name
            holder.textViewAge.text = studentList[position].age
            holder.textViewKorScore.text = studentList[position].korScore
        }
    }
}

data class Student(val name: String, val age: String, val korScore: String)