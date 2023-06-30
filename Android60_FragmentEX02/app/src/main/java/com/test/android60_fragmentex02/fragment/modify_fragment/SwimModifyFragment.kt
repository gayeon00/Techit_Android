package com.test.android60_fragmentex02.fragment.modify_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.SwimPlayer
import com.test.android60_fragmentex02.databinding.FragmentSwimModifyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SwimModifyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SwimModifyFragment : Fragment() {
    lateinit var fragmentSwimModifyBinding: FragmentSwimModifyBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentSwimModifyBinding = FragmentSwimModifyBinding.inflate(layoutInflater)
        val student = mainActivity.student as SwimPlayer

        fragmentSwimModifyBinding.run {
            textViewSWMName.text = student.name
            textViewSWMType.text = student.swimType
        }
        return fragmentSwimModifyBinding.root
    }

}