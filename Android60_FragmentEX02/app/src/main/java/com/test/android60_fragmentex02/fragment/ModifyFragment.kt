package com.test.android60_fragmentex02.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.test.android60_fragmentex02.BaseballPlayer
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.SoccerPlayer
import com.test.android60_fragmentex02.SwimPlayer
import com.test.android60_fragmentex02.databinding.FragmentModifyBinding
import com.test.android60_fragmentex02.fragment.input_fragment.BaseballInputFragment
import com.test.android60_fragmentex02.fragment.input_fragment.SoccerInputFragment
import com.test.android60_fragmentex02.fragment.input_fragment.SwimInputFragment
import com.test.android60_fragmentex02.fragment.modify_fragment.BaseballModifyFragment
import com.test.android60_fragmentex02.fragment.modify_fragment.SoccerModifyFragment
import com.test.android60_fragmentex02.fragment.modify_fragment.SwimModifyFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ModifyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModifyFragment : Fragment() {
    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentModifyBinding = FragmentModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        when(mainActivity.student) {
            is BaseballPlayer -> replaceFragment(FragmentModifyName.FRAGMENT_BASEBALL, true, true)
            is SoccerPlayer -> replaceFragment(FragmentModifyName.FRAGMENT_SOCCER, true, true)
            is SwimPlayer -> replaceFragment(FragmentModifyName.FRAGMENT_SWIM, true, true)
        }
        // Inflate the layout for this fragment
        return fragmentModifyBinding.root
    }

    private fun replaceFragment(name: FragmentModifyName, addToBackStack: Boolean, animate: Boolean) {
        //Fragment 교체 상태로 설정
        val fragmentTransaction = childFragmentManager.beginTransaction()
        //새로운 fragment 담을 변수

        //이름으로 분기
        val newFragment: Fragment = when (name) {
            FragmentModifyName.FRAGMENT_BASEBALL -> {
                //fragment 객체를 생성한다
                BaseballModifyFragment()
            }

            FragmentModifyName.FRAGMENT_SOCCER -> {
                SoccerModifyFragment()
            }

            FragmentModifyName.FRAGMENT_SWIM -> {
                SwimModifyFragment()
            }
        }

        //mainFragment가 보여지도록 fragment를 교체한다.
        fragmentTransaction.replace(R.id.fragmentContainerView3, newFragment)

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
enum class FragmentModifyName(val str: String) {
    FRAGMENT_BASEBALL("BaseballModifyFragment"),
    FRAGMENT_SOCCER("SoccerModifyFragment"),
    FRAGMENT_SWIM("SwimModifyFragment")
}