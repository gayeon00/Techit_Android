package com.test.android79_miniproject02.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.android79_miniproject02.MainActivity
import com.test.android79_miniproject02.R
import com.test.android79_miniproject02.databinding.FragmentSetPasswordBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SetPasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetPasswordFragment : Fragment() {
    lateinit var fragmentSetPasswordBinding: FragmentSetPasswordBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSetPasswordBinding = FragmentSetPasswordBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        fragmentSetPasswordBinding.run {
            buttonSettingComplete.setOnClickListener {
                val password = editTextPassword.text.toString()
                val passwordChk = editTextPasswordCheck.text.toString()

                if (password == passwordChk) {

                } else {
                    Toast.makeText(
                        mainActivity,
                        "비밀번호가 일치하지 않습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        // Inflate the layout for this fragment
        return fragmentSetPasswordBinding.root
    }

}