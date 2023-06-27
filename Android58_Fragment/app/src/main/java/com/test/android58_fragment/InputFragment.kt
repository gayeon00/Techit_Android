package com.test.android58_fragment

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import com.test.android58_fragment.databinding.FragmentInputBinding
import kotlin.concurrent.thread

class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)


        fragmentInputBinding.run {
            editTextInputUserName.requestFocus()

            thread {
                SystemClock.sleep(1000)
                val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextInputUserName, 0)
            }


            editTextInputUserKorean.setOnEditorActionListener { textView, i, keyEvent ->
                mainActivity.personList.add(Person(
                    editTextInputUserName.text.toString(),
                    editTextInputUserAge.text.toString().toInt(),
                    textView.text.toString().toInt()
                ))

                //즉시 프래그먼트를 제거
                mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                false
            }
        }

//        val callback = requireActivity().onBackPressedDispatcher.addCallback(object :
//            OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
//            }
//        })
        return fragmentInputBinding.root
    }

}