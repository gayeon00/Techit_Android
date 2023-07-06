package com.test.android78_miniproject01.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android78_miniproject01.MainActivity
import com.test.android78_miniproject01.Memo
import com.test.android78_miniproject01.databinding.FragmentMainBinding
import com.test.android78_miniproject01.databinding.RowBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    var memoList = mutableListOf<Memo>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        memoList = mainActivity.getAllMemo()

        fragmentMainBinding.run {
            recyclerView.run {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        mainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
            toolbarMain.run {
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.INPUT_FRAGMENT, true, true)
                    false
                }
            }
        }
        // Inflate the layout for this fragment
        return fragmentMainBinding.root
    }

    inner class RecyclerViewAdapter: Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowBinding): ViewHolder(rowBinding.root) {
            val textViewRowDate = rowBinding.textViewRowDate
            val textViewRowTitle = rowBinding.textViewRowTitle

            init {
                rowBinding.root.setOnClickListener {
                    //클릭 시
                    mainActivity.rowPosition = adapterPosition
                    mainActivity.replaceFragment(MainActivity.SHOW_FRAGMENT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val recyclerViewHolder = RecyclerViewHolder(rowBinding)

            rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return recyclerViewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.textViewRowDate.text = memoList[position].date
            holder.textViewRowTitle.text = memoList[position].title
        }
    }

}