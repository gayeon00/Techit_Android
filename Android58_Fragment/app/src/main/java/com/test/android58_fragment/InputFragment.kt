package com.test.android58_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.test.android58_fragment.databinding.FragmentInputBinding

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

            editTextInputUserKorean.setOnEditorActionListener { textView, i, keyEvent ->
                mainActivity.personList.add(Person(
                    editTextInputUserName.text.toString(),
                    editTextInputUserAge.text.toString().toInt(),
                    textView.text.toString().toInt()
                ))

                mainActivity.replaceFragment(FragmentName.FRAGMENT_RESULT, true, true)
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