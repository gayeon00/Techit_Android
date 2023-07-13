package com.test.android79_miniproject02.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.android79_miniproject02.MainActivity
import com.test.android79_miniproject02.databinding.FragmentLogInBinding
import com.test.android79_miniproject02.ui.CategoryListActivity

class LogInFragment : Fragment() {
    lateinit var fragmentLogInBinding: FragmentLogInBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLogInBinding = FragmentLogInBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentLogInBinding.run {
            buttonLogIn.setOnClickListener {
                val password = getPassword()
                val inputPassword = editTextPasswordLogIn.text.toString()

                if (password == inputPassword) {
                    startActivity(Intent(context, CategoryListActivity::class.java))
                    Toast.makeText(
                        mainActivity,
                        "로그인에 성공했습니다 :)",
                        Toast.LENGTH_SHORT
                    ).show()
                    mainActivity.finish()

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
        return fragmentLogInBinding.root
    }

    private fun getPassword(): String? {
        val pref = mainActivity.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)

        return pref.getString("password", "")
    }

}