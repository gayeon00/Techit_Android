package com.test.android20_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.android20_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            radioGroupZoo.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    R.id.radioButtonElephant -> Log.d("라디오", "코끼리")
                    R.id.radioButtonCat -> Log.d("라디오", "고양이")
                    R.id.radioButtonDog -> Log.d("라디오", "강아지")
                }
            }
        }
    }

    abstract class Animal(val animalType: String, val food: String) {
        abstract fun printAnimalInfo(): String
    }

    class Elephant(val name: String, val lengthOfNose: String) : Animal("코끼리", "나뭇잎") {
        override fun printAnimalInfo(): String {
            return """
                동물 종류 : $animalType\n
                먹이 : $food\n
                이름 : $name\n
                코의 길이 : $lengthOfNose\n
            """.trimIndent()
        }

    }
}