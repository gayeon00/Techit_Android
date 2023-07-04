package com.test.android71_viewex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.test.android71_viewex01.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        // Inflate the layout for this fragment
        val person = mainActivity.dataList[mainActivity.position]

        fragmentResultBinding.run {
            textViewName.text = person.name
            textViewAge.text = person.age.toString()
            textViewHobbies.text = person.hobby
        }

        return fragmentResultBinding.root
    }
}