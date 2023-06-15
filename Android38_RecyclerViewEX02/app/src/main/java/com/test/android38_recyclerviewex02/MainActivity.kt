package com.test.android38_recyclerviewex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android38_recyclerviewex02.databinding.ActivityMainBinding
import com.test.android38_recyclerviewex02.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val studentList = mutableListOf<Student>()


    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            //추가 버튼을 누르면
            buttonInput.setOnClickListener {
                linearLayoutShow.visibility = View.GONE
                linearLatoutInput.visibility = View.VISIBLE

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                editTextName.requestFocus()
                imm.showSoftInput(editTextName,InputMethodManager.SHOW_IMPLICIT)
            }

            //보기 버튼을 누르면
            buttonShow.setOnClickListener {
                if(currentFocus!=null){
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                }

                linearLayoutShow.visibility = View.VISIBLE
                linearLatoutInput.visibility = View.GONE
            }

            //마지막 입력칸에서 엔터키 누름
            editTextKorScore.setOnEditorActionListener { textView, i, keyEvent ->
                val student = Student(editTextName.text.toString(), editTextAge.text.toString(), editTextKorScore.text.toString())
                studentList.add(student)

                val adapter = recyclerView.adapter as RecyclerViewAdapterClass
                adapter.notifyDataSetChanged()

                editTextName.setText("")
                editTextAge.setText("")
                editTextKorScore.setText("")

                editTextName.requestFocus()

                false
            }

            recyclerView.run {
                adapter = RecyclerViewAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

        }
    }

    inner class RecyclerViewAdapterClass: Adapter<RecyclerViewAdapterClass.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : ViewHolder(rowBinding.root){
            var textViewName = rowBinding.textViewName
            var textViewAge = rowBinding.textViewAge
            var textViewKorScore = rowBinding.textViewKorScore
            var buttonDelete = rowBinding.buttonDelete
        }

        //뷰홀더 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)

            //항목 View의 가로세로길이를 설정(터치 때문)
            val params = RecyclerView.LayoutParams(
                //가로 길이
                RecyclerView.LayoutParams.MATCH_PARENT,
                //세로길이
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params


            return ViewHolderClass(rowBinding)
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        //viewHolder에 묶어주기
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewName.text = studentList[position].name
            holder.textViewAge.text = "${studentList[position].age}세"
            holder.textViewKorScore.text = "${studentList[position].korScore}점"

            holder.buttonDelete.setOnClickListener {
                studentList.removeAt(position)

                this.notifyDataSetChanged()
            }
        }
    }
}

data class Student (val name: String, val age: String, val korScore: String)