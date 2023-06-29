package com.test.android60_fragmentex02.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android60_fragmentex02.BaseballPlayer
import com.test.android60_fragmentex02.FragmentName
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.SoccerPlayer
import com.test.android60_fragmentex02.Student
import com.test.android60_fragmentex02.SwimPlayer
import com.test.android60_fragmentex02.databinding.FragmentMainBinding
import com.test.android60_fragmentex02.databinding.RowBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActiviy: MainActivity

    val teams = arrayOf(
        "전체보기", "야구부", "축구부", "수영부"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActiviy = activity as MainActivity
        fragmentMainBinding.run {
            val myAdapter = RecyclerViewAdapter()

            spinnerMainTeam.run {
                val a1 = ArrayAdapter(
                    mainActiviy, android.R.layout.simple_spinner_item, teams
                )
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                adapter = a1

                setSelection(0)

                onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        myAdapter.updateData(p2)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
            }

            recyclerView.run {
                adapter = myAdapter
                layoutManager = LinearLayoutManager(mainActiviy)

                addItemDecoration(DividerItemDecoration(mainActiviy, LinearLayoutManager.VERTICAL))
            }

            buttonToInput.setOnClickListener {
                mainActiviy.replaceFragment(FragmentName.FRAGMENT_INPUT, true, false)
            }
        }
        // Inflate the layout for this fragment
        return fragmentMainBinding.root
    }

    inner class RecyclerViewAdapter : Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        var students = emptyList<Student>()
        inner class RecyclerViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root) {
            val textViewRowTeam = rowBinding.textViewRowTeam
            val textViewRowName = rowBinding.textViewRowName

            init {
                rowBinding.root.setOnClickListener {
                    mainActiviy.student = students[adapterPosition]
                    mainActiviy.replaceFragment(FragmentName.FRAGMENT_SHOW, true, true)
                }
            }
        }

        fun updateData(filter: Int) {
            students = when (filter) {
                0 -> mainActiviy.studentList
                1 -> mainActiviy.studentList.filterIsInstance<BaseballPlayer>()
                2 -> mainActiviy.studentList.filterIsInstance<SoccerPlayer>()
                3 -> mainActiviy.studentList.filterIsInstance<SwimPlayer>()
                else -> emptyList()
            }
            notifyDataSetChanged()
        }

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
            return students.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val student = students.getOrNull(position)
            if (student != null) {
                holder.textViewRowName.text = student.name
                holder.textViewRowTeam.text = student.team
            } else {
                holder.textViewRowName.text = "학생을 추가해 주세요."
                holder.textViewRowTeam.text = ""
            }
        }
    }
}