package com.test.android80_miniproject03.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.android80_miniproject03.R
import com.test.android80_miniproject03.databinding.ActivitySignUpBinding
import com.test.android80_miniproject03.db.UserDao
import com.test.android80_miniproject03.shop.ShopActivity

class SignUpActivity : AppCompatActivity() {
    lateinit var activitySignUpBinding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activitySignUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activitySignUpBinding.root)

        activitySignUpBinding.run {
            buttonSignUpComplete.setOnClickListener {
                val email = editTextEmailAddressSignUp.text.toString()
                val password = editTextPasswordSignUp.text.toString()
                val passwordRe = editTextPasswordReSignUp.text.toString()
                val name = editTextNameSignUp.text.toString()
                val userName = editTextUserNameSignUp.text.toString()

                //이미 email이 user database에 존재하는 경우
                if(isEmailExists(email)) {
                    Toast.makeText(this@SignUpActivity, "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show()
                } else if (password != passwordRe) {
                    Toast.makeText(this@SignUpActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    val user = User(email, password, name, userName)
                    //db에 추가하기
                    UserDao.addUser(this@SignUpActivity, user)
                    // 일치 하면 메인화면으로
                    val shopIntent = Intent(this@SignUpActivity, ShopActivity::class.java)
                    shopIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(shopIntent)
                    //이거, MainActivity 종료하기
                    finish()
                }
            }
        }
    }

    private fun isEmailExists(email: String) = UserDao.isEmailExists(this@SignUpActivity, email)
}