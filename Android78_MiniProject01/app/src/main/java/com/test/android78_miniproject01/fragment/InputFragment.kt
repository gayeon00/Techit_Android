package com.test.android78_miniproject01.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android78_miniproject01.MainActivity
import com.test.android78_miniproject01.Memo
import com.test.android78_miniproject01.R
import com.test.android78_miniproject01.databinding.FragmentInputBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentInputBinding.run {
            toolbarInput.run {
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.INPUT_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    //저장하기
                    val name = editTextTitle.text.toString()
                    val content = editTextContent.text.toString()

                    val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
                    //현재시간 갖고있는 데이터
                    val now = sdf.format(Date())

                    mainActivity.insertMemo(Memo(name, content, now))

                    mainActivity.removeFragment(MainActivity.INPUT_FRAGMENT)
                    false
                }
            }
        }

        return fragmentInputBinding.root
    }


}