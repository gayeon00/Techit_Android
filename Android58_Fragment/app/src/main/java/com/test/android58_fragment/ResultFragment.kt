package com.test.android58_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android58_fragment.databinding.FragmentResultBinding
import com.test.android58_fragment.databinding.RowBinding

class ResultFragment : Fragment() {
    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentResultBinding.run {
            recyclerView.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }
        // Inflate the layout for this fragment
        return fragmentResultBinding.root
    }

    inner class RecyclerViewAdapter: Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        inner class RecyclerViewHolder(rowBinding: RowBinding): ViewHolder(rowBinding.root){
            val textViewName = rowBinding.textViewName
            val textViewAge = rowBinding.textViewAge
            val textViewKor = rowBinding.textViewKor
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
            return mainActivity.personList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewName.text = mainActivity.personList[position].name
            holder.textViewAge.text = mainActivity.personList[position].age.toString()
            holder.textViewKor.text = mainActivity.personList[position].kor.toString()
        }
    }
}