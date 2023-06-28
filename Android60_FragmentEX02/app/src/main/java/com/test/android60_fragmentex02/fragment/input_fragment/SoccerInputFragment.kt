package com.test.android60_fragmentex02.fragment.input_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android60_fragmentex02.FragmentName
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.SoccerPlayer
import com.test.android60_fragmentex02.databinding.FragmentBaseballInputBinding
import com.test.android60_fragmentex02.databinding.FragmentSoccerInputBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SoccerInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SoccerInputFragment : Fragment() {
    lateinit var fragmentSoccerInputBinding: FragmentSoccerInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSoccerInputBinding = FragmentSoccerInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentSoccerInputBinding.run {
            editTextSCHCount.setOnEditorActionListener { textView, i, keyEvent ->
                mainActivity.studentList.add(
                    SoccerPlayer(
                        editTextSCName.text.toString(),
                        editTextSCGcount.text.toString().toInt(),
                        editTextSCHCount.text.toString().toInt()
                    )
                )
                Log.d("now", mainActivity.studentList.toString())
                mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                false
            }
        }

        return fragmentSoccerInputBinding.root
    }

}