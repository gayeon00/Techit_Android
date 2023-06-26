package com.test.android58_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.test.android58_fragment.databinding.FragmentMainBinding
import kotlinx.coroutines.delay

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    //Fragment가 보여줄 화면을 생성하는 메서드
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        //fragment를 관리하는 Activity 객체 가져오기
        mainActivity = activity as MainActivity
        //viewbinding
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

        fragmentMainBinding.run {
            buttonMainFragment1.setOnClickListener {
                //Fragment 교체
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)
            }
        }

        return fragmentMainBinding.root
    }
}