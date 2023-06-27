package com.test.android59_fragmentex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android59_fragmentex01.databinding.FragmentMainBinding
import com.test.android59_fragmentex01.databinding.RowBinding

class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        // Inflate the layout for this fragment

        fragmentMainBinding.run {
            recyclerView.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
            buttonAdd.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)
            }
        }
        return fragmentMainBinding.root
    }

    inner class RecyclerViewAdapter : Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root) {
            val textViewType = rowBinding.textViewRowAnimalType
            val textViewName = rowBinding.textViewRowName

            init {
                rowBinding.root.setOnClickListener {
                    mainActivity.rowPosition = adapterPosition
                    mainActivity.replaceFragment(FragmentName.FRAGMENT_RESULT, true, true)
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
            return mainActivity.animalList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewType.text = mainActivity.animalList[position].type
            holder.textViewName.text = mainActivity.animalList[position].name
        }
    }
}