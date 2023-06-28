package com.test.android60_fragmentex02.fragment.input_fragment

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
import com.test.android60_fragmentex02.databinding.FragmentBaseballInputBinding
import com.test.android60_fragmentex02.fragment.InputFragment

class BaseballInputFragment : Fragment() {
    lateinit var fragmentBaseballInputBinding: FragmentBaseballInputBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBaseballInputBinding = FragmentBaseballInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentBaseballInputBinding.run {
            editTextBBACount.setOnEditorActionListener { textView, i, keyEvent ->
                mainActivity.studentList.add(
                    BaseballPlayer(
                        editTextBBName.text.toString(),
                        editTextBBBattingAvg.text.toString().toDouble(),
                        editTextBBHCount.text.toString().toInt(),
                        editTextBBACount.text.toString().toInt()
                    )
                )
                Log.d("now", mainActivity.studentList.toString())

                mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                false
            }
        }
        // Inflate the layout for this fragment
        return fragmentBaseballInputBinding.root
    }

}