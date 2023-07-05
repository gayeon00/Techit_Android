package com.test.android78_sqliteex01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android78_sqliteex01.MainActivity
import com.test.android78_sqliteex01.R
import com.test.android78_sqliteex01.Student
import com.test.android78_sqliteex01.databinding.FragmentMainBinding
import com.test.android78_sqliteex01.databinding.RowBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 학생의 정보를 담을 리스트
    var studentList = mutableListOf<Student>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        studentList = mainActivity.getAllStudent()

        fragmentMainBinding.run {
            toolbarMain.run {
                title = "MainFragment"
                inflateMenu(R.menu.main)
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.INPUT_FRAGMENT, true, true)
                    false
                }
            }

            recyclerViewMain.run {
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        mainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }

        }


        // Inflate the layout for this fragment
        return fragmentMainBinding.root
    }

    inner class MainRecyclerViewAdapter :
        RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>() {

        inner class MainViewHolderClass(rowBinding: RowBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            var textViewMainRowName = rowBinding.textViewMainRowName

            init {

                rowBinding.root.setOnClickListener {
                    mainActivity.rowPosition = adapterPosition
                    mainActivity.replaceFragment(MainActivity.SHOW_FRAGMENT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            val rowMainBinding = RowBinding.inflate(layoutInflater)
            val mainViewHolderClass = MainViewHolderClass(rowMainBinding)

            rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return mainViewHolderClass
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.textViewMainRowName.text = studentList[position].name
        }
    }
}