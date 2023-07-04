package com.test.android71_viewex01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.test.android71_viewex01.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    var index = 1
    val viewList = mutableListOf<EditText>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentInputBinding.run {
            toolbar2.run {
                inflateMenu(R.menu.input)

                buttonAddHobby.setOnClickListener {
                    val params = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    val newEditTextHobby = EditText(mainActivity)
                    newEditTextHobby.layoutParams = params
                    newEditTextHobby.hint = "취미${index++}"

                    viewList.add(newEditTextHobby)
                    fragmentInputBinding.hobbyContainer.addView(newEditTextHobby)

                }

                setOnMenuItemClickListener {
                    var hobbyList = ""
                    for(v in viewList) {
                        hobbyList += "${v.text}, "
                    }

                    mainActivity.dataList.add(
                        Person(
                            editTextName.text.toString(),
                            editTextAge.text.toString().toInt(),
                            hobbyList
                        )
                    )
                    Log.d("mydata", mainActivity.dataList.toString())
                    mainActivity.removeFragment(MainActivity.FragmentName.FRAGMENT_INPUT)
                    false
                }
            }
        }
        // Inflate the layout for this fragment
        return fragmentInputBinding.root
    }

}