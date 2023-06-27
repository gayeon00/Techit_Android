package com.test.android60_fragmentex02.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
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
            spinnerMainTeam.run {
                val a1 = ArrayAdapter(
                    mainActiviy, android.R.layout.simple_spinner_item, teams
                )
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                adapter = a1

                setSelection(0)
            }

            recyclerView.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActiviy)
            }

            buttonToInput.setOnClickListener {
                mainActiviy.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)
            }
        }
        // Inflate the layout for this fragment
        return fragmentMainBinding.root
    }

    inner class RecyclerViewAdapter : Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        var studentList = emptyList<Student>()
        inner class RecyclerViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root) {
            val textViewRowTeam = rowBinding.textViewRowTeam
            val textViewRowName = rowBinding.textViewRowName

            init {
                rowBinding.root.setOnClickListener {

                }
            }
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
            return studentList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

            when (fragmentMainBinding.spinnerMainTeam.selectedItemPosition) {
                0 -> {
                    studentList = mainActiviy.studentList
                    holder.textViewRowTeam.text = teams[0]
                }

                1 -> {
                    studentList = mainActiviy.studentList.filterIsInstance<BaseballPlayer>()
                    holder.textViewRowTeam.text = teams[1]
                }

                2 -> {
                    studentList = mainActiviy.studentList.filterIsInstance<SoccerPlayer>()
                    holder.textViewRowTeam.text = teams[2]
                }

                3 -> {
                    studentList = mainActiviy.studentList.filterIsInstance<SwimPlayer>()
                    holder.textViewRowTeam.text = teams[3]
                }
            }
            holder.textViewRowName.text = if (studentList.isNotEmpty()) {
                studentList[position].name
            } else {
                "학생을 추가해 주세요."
            }
        }
    }
}