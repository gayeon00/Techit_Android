package com.test.android60_fragmentex02.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentTransaction
import com.test.android60_fragmentex02.FragmentName
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.databinding.FragmentInputBinding
import com.test.android60_fragmentex02.fragment.input_fragment.BaseballInputFragment
import com.test.android60_fragmentex02.fragment.input_fragment.SoccerInputFragment
import com.test.android60_fragmentex02.fragment.input_fragment.SwimInputFragment

class InputFragment : Fragment() {

    val inputList = arrayOf(
        "야구", "축구", "수영"
    )

    var fragmentName = ""

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        // Inflate the layout for this fragment

        fragmentInputBinding.run {
            spinnerInputTeam.run {
                val a1 = ArrayAdapter(
                    context, android.R.layout.simple_spinner_item, inputList
                )
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                adapter = a1

                setSelection(0)

                onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        replaceFragment(FragmentInputName.values()[p2], false, false)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
            }
        }


        return fragmentInputBinding.root


    }

    fun replaceFragment(name: FragmentInputName, addToBackStack: Boolean, animate: Boolean) {
        //Fragment 교체 상태로 설정
        val fragmentTransaction = childFragmentManager.beginTransaction()
        //새로운 fragment 담을 변수

        //이름으로 분기
        val newFragment: Fragment = when (name) {
            FragmentInputName.FRAGMENT_BASEBALL -> {
                //fragment 객체를 생성한다
                BaseballInputFragment()
            }

            FragmentInputName.FRAGMENT_SOCCER -> {
                SoccerInputFragment()
            }

            FragmentInputName.FRAGMENT_SWIM -> {
                SwimInputFragment()
            }
        }

        //mainFragment가 보여지도록 fragment를 교체한다.
        fragmentTransaction.replace(R.id.fragmentContainerView2, newFragment)

        if (animate) {
            //애니메이션 설정
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        if (addToBackStack) {
            //fragment를 backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도로 함
            //이름도 넣어줘서 뺄때 해당 이름으로 뺄 수 있도록
            fragmentTransaction.addToBackStack(name.str)
        }

        //교체명령 동작
        fragmentTransaction.commit()
    }
}

//fragment를 구분하기 위한 이름
enum class FragmentInputName(val str: String) {
    FRAGMENT_BASEBALL("BaseballInputFragment"),
    FRAGMENT_SOCCER("SoccerInputFragment"),
    FRAGMENT_SWIM("SwimInputFragment")
}