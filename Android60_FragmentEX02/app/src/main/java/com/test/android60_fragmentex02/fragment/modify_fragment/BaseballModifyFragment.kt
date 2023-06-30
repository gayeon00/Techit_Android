package com.test.android60_fragmentex02.fragment.modify_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android60_fragmentex02.BaseballPlayer
import com.test.android60_fragmentex02.FragmentName
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.databinding.FragmentBaseballModifyBinding

class BaseballModifyFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var fragmentBaseballModifyBinding: FragmentBaseballModifyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBaseballModifyBinding = FragmentBaseballModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        val student = mainActivity.student as BaseballPlayer

        fragmentBaseballModifyBinding.run {
            textViewBBName.text = student.name
            textViewBBBattingAvg.text = student.battingAvg.toString()
            textViewBBACount.text = student.aCount.toString()
            textViewBBHCount.text = student.hCount.toString()

            buttonModifyComplete.setOnClickListener {
                val index = mainActivity.studentList.indexOf(student)
                val newStudent = createNewBaseballPlayer()
                mainActivity.student = newStudent
                mainActivity.studentList[index] = newStudent

                mainActivity.removeFragment(FragmentName.FRAGMENT_MODIFY)
            }


            buttonModifyCancel.setOnClickListener {
                mainActivity.removeFragment(FragmentName.FRAGMENT_MODIFY)
            }
        }
        return fragmentBaseballModifyBinding.root
    }

    private fun FragmentBaseballModifyBinding.createNewBaseballPlayer() =
        BaseballPlayer(
            editTextBBName.text.toString(),
            editTextBBBattingAvg.text.toString().toDouble(),
            editTextBBHCount.text.toString().toInt(),
            editTextBBACount.text.toString().toInt()
        )
}