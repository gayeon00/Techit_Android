package com.test.android46_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android46_ex02.databinding.ActivityFruitDetailBinding

class FruitDetailActivity : AppCompatActivity() {
    //TODO intent로 부터 받아오기
    lateinit var activityFruitDetailBinding: ActivityFruitDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityFruitDetailBinding = ActivityFruitDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityFruitDetailBinding.root)


        activityFruitDetailBinding.run {
            val fruit = intent.getParcelableExtra<Fruit>("fruit")

            if (fruit != null) {
                textViewDetailName.text = fruit.name
                textViewDetailCount.text = "${fruit.count.toString()}개"
                textViewDetailOrigin.text = fruit.origin
            }

            buttonGoBackToMain.setOnClickListener {
                finish()
            }
        }
    }
}