package com.test.android78_sqliteex01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.android78_sqliteex01.MainActivity
import com.test.android78_sqliteex01.Student
import com.test.android78_sqliteex01.databinding.FragmentInputBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentInputBinding.run {
            editTextKorean.setOnEditorActionListener { textView, i, keyEvent ->
                val name = editTextAddName.text.toString()
                val age = editTextAddAge.text.toString().toInt()
                val korean = editTextKorean.text.toString().toInt()

                val student = Student(name, age, korean)

                mainActivity.insertStudent(student)
                //TODO : 학생 데이터 저장하기

                mainActivity.removeFragment(MainActivity.INPUT_FRAGMENT)

                false
            }
        }

        return fragmentInputBinding.root
    }

}