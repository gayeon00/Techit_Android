package com.test.android71_viewex01

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android71_viewex01.databinding.FragmentMainBinding
import com.test.android71_viewex01.databinding.RowBinding

class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        // Inflate the layout for this fragment

        fragmentMainBinding.run {
            recyclerView.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)

                addItemDecoration(DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL))

            }
            toolbar.run {
                title = "예제"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main)

                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_INPUT, true, true)
                    false
                }
            }
        }

        return fragmentMainBinding.root
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            val textViewRowName = rowBinding.textViewRowName

            init {
                rowBinding.root.setOnClickListener {
                    mainActivity.position = adapterPosition
                    mainActivity.replaceFragment(MainActivity.FragmentName.FRAGMENT_RESULT, true, true)
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
            return mainActivity.dataList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewRowName.text = mainActivity.dataList[position].name
        }
    }
}