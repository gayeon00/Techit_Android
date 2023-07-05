package com.test.android78_sqliteex01.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android78_sqliteex01.MainActivity
import com.test.android78_sqliteex01.databinding.FragmentShowBinding


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
        // Inflate the layout for this fragment

        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val studentList = mainActivity.getAllStudent()

        // 선택한 행 번째의 객체에서 데이터를 가져와 출력한다.
        fragmentShowBinding.run{
            textViewResult1.text = studentList[mainActivity.rowPosition].name
            textViewResult2.text = studentList[mainActivity.rowPosition].age.toString()
            textViewResult3.text = studentList[mainActivity.rowPosition].korean.toString()
        }

        return fragmentShowBinding.root
    }

}