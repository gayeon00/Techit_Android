package com.test.android65_toolbarex01

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android65_toolbarex01.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)

        fragmentInputBinding.run {
            toolbar2.run {
                title = "Input"
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    //투명색이 아닌 부분을 흰색으로 채워주기
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                }
            }

            button.setOnClickListener {
                mainActivity.personList.add(
                    Person(
                        editTextName.text.toString(),
                        editTextAge.text.toString(),
                        editTextKor.text.toString()
                    )
                )
                Log.d("now", mainActivity.personList.toString())
                mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
            }
        }
        // Inflate the layout for this fragment
        return fragmentInputBinding.root
    }
}