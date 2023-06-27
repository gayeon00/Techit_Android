package com.test.android59_fragmentex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.test.android59_fragmentex01.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    val animalList = arrayOf("코끼리", "기린", "토끼")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        // Inflate the layout for this fragment

        fragmentInputBinding.run {
            spinnerAnimalType.run {
                val a1 = ArrayAdapter(
                    mainActivity, android.R.layout.simple_spinner_item, animalList
                )
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                adapter = a1

                setSelection(0)
            }

            editTextAge.addTextChangedListener {
                if(it!!.isEmpty()){
                    textInputLayoutAge.error = "나이를 0자 이상 입력해주세요"
                } else {
                    textInputLayoutAge.error = null
                }
            }

            editTextWeight.addTextChangedListener {
                if(it!!.isEmpty()){
                    textInputLayoutWeight.error = "몸무게를 0자 이상 입력해주세요"
                } else {
                    textInputLayoutWeight.error = null
                }
            }

            editTextWeight.setOnEditorActionListener { textView, i, keyEvent ->
                mainActivity.animalList.add(
                    Animal(
                        animalList[spinnerAnimalType.selectedItemPosition],
                        editTextName.text.toString(),
                        editTextAge.text.toString().toInt(),
                        editTextWeight.text.toString().toInt()
                    )
                )

                mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                false
            }
        }
        return fragmentInputBinding.root
    }
}