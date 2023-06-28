package com.test.android65_toolbarex01

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android65_toolbarex01.databinding.FragmentMainBinding
import com.test.android65_toolbarex01.databinding.RowBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run {
            toolbar.run {
                title = "ToolBar"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    if(it?.itemId == R.id.item1) {
                        //InputFragment로 바뀌기
                        mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, false)
                    }
                    false
                }
            }

            recyclerView.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)

                addItemDecoration(DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL))
            }
        }
        // Inflate the layout for this fragment
        return fragmentMainBinding.root
    }

    inner class RecyclerViewAdapter: Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowBinding):ViewHolder(rowBinding.root) {
            val textViewName = rowBinding.textView

            init {
                rowBinding.root.setOnClickListener {
                    mainActivity.replaceFragment(FragmentName.FRAGMENT_RESULT, true, false)
                    mainActivity.position = adapterPosition
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
            return mainActivity.personList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewName.text = mainActivity.personList[position].name
        }
    }
}