package com.test.android60_fragmentex02.fragment.modify_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.SoccerPlayer
import com.test.android60_fragmentex02.databinding.FragmentSoccerModifyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SoccerModifyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SoccerModifyFragment : Fragment() {
    lateinit var fragmentSoccerModifyBinding: FragmentSoccerModifyBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSoccerModifyBinding = FragmentSoccerModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        val student = mainActivity.student as SoccerPlayer
        fragmentSoccerModifyBinding.run {
            textViewSCMName.text = student.name
            textViewSCMGcount.text = student.goalCount.toString()
            textViewSCMHcount.text = student.helpCount.toString()
        }
        return fragmentSoccerModifyBinding.root
    }

}