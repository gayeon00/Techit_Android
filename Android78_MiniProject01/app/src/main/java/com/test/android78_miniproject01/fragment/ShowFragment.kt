package com.test.android78_miniproject01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.android78_miniproject01.MainActivity
import com.test.android78_miniproject01.R
import com.test.android78_miniproject01.databinding.FragmentShowBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowFragment : Fragment() {
    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        val memoList = mainActivity.getAllMemo()
        val memo = memoList[mainActivity.rowPosition]

        fragmentShowBinding.run {
            toolbarShow.run {
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.SHOW_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.itemEdit -> {
                            mainActivity.replaceFragment(MainActivity.EDIT_FRAGMENT, true, true)
                            //mainActivity.updateData(memo, mainActivity.rowPosition)
                        }

                        R.id.itemDelete -> {
                            //지우기
                            mainActivity.deleteMemo(memo)
                            mainActivity.removeFragment(MainActivity.SHOW_FRAGMENT)
                        }
                    }
                    false
                }
            }
            textViewTitle.text = memo.title
            textViewContent.text = memo.content
            textViewDate.text = memo.date
        }


        // Inflate the layout for this fragment
        return fragmentShowBinding.root
    }

}