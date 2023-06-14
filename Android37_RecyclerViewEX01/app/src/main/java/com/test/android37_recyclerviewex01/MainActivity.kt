package com.test.android37_recyclerviewex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.test.android37_recyclerviewex01.databinding.ActivityMainBinding
import com.test.android37_recyclerviewex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val dataList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            editTextKorScore.setOnEditorActionListener { textView, i, keyEvent ->
                val student = Student(editTextName.text.toString(), editTextAge.text.toString().toInt(), editTextKorScore.text.toString().toInt())
                dataList.add(student)

                editTextAge.setText("")
                editTextName.setText("")
                editTextKorScore.setText("")

                //adapter notify?
                false
            }

            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    // RecyclerView의 AdapterClass
    // 1. 아무것도 상속받지 않은 RecyclerAdapterClass 클래스를 만들어준다.
    // 2. inner class로 ViewHolder를 상속받는 ViewHolderClass를 만들어준다.
    // 3. AdapterClass를 RecyclerView.Adapter를 상속하게 한다. 제네릭으로 앞서 만든 ViewHolderClass을 넣어준다 -> 오류 뜨는건 suggestion따라 imple해주면 됨
    // 4. 필요한 메서드들을 구현한다.

    inner class RecyclerAdapterClass: Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            var textViewName: TextView
            var textViewAge: TextView
            var textViewKorScore: TextView

            init {
                textViewName = rowBinding.textViewRowName
                textViewAge = rowBinding.textViewRowAge
                textViewKorScore = rowBinding.textViewRowKorScore
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            //항목 View의 가로세로길이를 설정(터치 때문)
            val params = RecyclerView.LayoutParams(
                //가로 길이
                RecyclerView.LayoutParams.MATCH_PARENT,
                //세로길이
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewName.text = dataList[position].name
            holder.textViewAge.text = dataList[position].age.toString()
            holder.textViewKorScore.text = dataList[position].age.toString()
        }
    }
}

data class Student(val name: String, val age: Int, val korScore: Int)
