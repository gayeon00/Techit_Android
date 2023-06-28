package com.test.android65_toolbarex01

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android65_toolbarex01.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentResultBinding.run {
            val person = mainActivity.personList[mainActivity.position]
            textViewName.text = person.name
            textViewAge.text = person.age
            textViewKor.text = person.kor

            toolbar3.run {
                title = "Result"
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    //투명색이 아닌 부분을 흰색으로 채워주기
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
                }
            }
        }
        // Inflate the layout for this fragment
        return fragmentResultBinding.root
    }

}