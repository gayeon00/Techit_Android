package com.test.android60_fragmentex02.fragment.modify_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android60_fragmentex02.BaseballPlayer
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.databinding.FragmentBaseballModifyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BaseballModifyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        Log.d("student", student.toString())
        fragmentBaseballModifyBinding.run {
            textViewBBName.text = student.name
            textViewBBBattingAvg.text = student.battingAvg.toString()
            textViewBBACount.text = student.aCount.toString()
            textViewBBHCount.text = student.hCount.toString()
        }
        return fragmentBaseballModifyBinding.root
    }
}