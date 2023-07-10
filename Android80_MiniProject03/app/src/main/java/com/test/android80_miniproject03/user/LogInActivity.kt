package com.test.android80_miniproject03.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.android80_miniproject03.R
import com.test.android80_miniproject03.databinding.ActivityLogInBinding
import com.test.android80_miniproject03.db.UserDao
import com.test.android80_miniproject03.shop.ShopActivity

class LogInActivity : AppCompatActivity() {
    lateinit var activityLogInBinding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityLogInBinding = ActivityLogInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityLogInBinding.root)

        activityLogInBinding.run {
            buttonLogInComplete.setOnClickListener {
                val email = editTextEmailAddressLogIn.text.toString()
                val password = editTextPassword.text.toString()

                //db에서 해당 email있는지 확인
                if(isEmailExists(email)) {
                    //있으면 password일치하는지 확인
                    if(isPasswordCorrect(email, password)) {
                        //일치 하면 메인화면으로
                        val shopIntent = Intent(this@LogInActivity, ShopActivity::class.java)
                        shopIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(shopIntent)
                        //이거, MainActivity 종료하기
                        finish()
                    } else {
                        //일치 하지 않으면 비밀번호 틀렸다고 하기
                        Toast.makeText(this@LogInActivity, "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    //없으면 가입하라고 하기
                    Toast.makeText(this@LogInActivity, "이메일이 존재하지 않습니다. 회원가입을 진행해주세요", Toast.LENGTH_SHORT).show()
                }




            }
        }
    }

    private fun isPasswordCorrect(email: String, password: String) =
        UserDao.isPasswordCorrect(this@LogInActivity, email, password)

    private fun isEmailExists(email: String) = UserDao.isEmailExists(this@LogInActivity, email)

}