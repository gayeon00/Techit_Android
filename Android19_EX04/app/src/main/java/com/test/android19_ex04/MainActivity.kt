package com.test.android19_ex04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android19_ex04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val students = mutableListOf<Student>()
        activityMainBinding.run {
            editTextKorScore.run{
                setOnEditorActionListener { textView, i, keyEvent ->
                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString().toInt()
                    val sex = when (radioGroupSex.checkedRadioButtonId) {
                        R.id.radioButtonFemale -> Sex.FEMALE
                        R.id.radioButtonMale -> Sex.MALE
                        else -> Sex.DEFAULT
                    }
                    val hobbies = mutableListOf<Hobby>()
                    if(checkBoxGame.isChecked){
                        hobbies.add(Hobby.GAME)
                    }
                    if(checkBoxSoccer.isChecked){
                        hobbies.add(Hobby.SOCCER)
                    }
                    if(checkBoxWatch.isChecked){
                        hobbies.add(Hobby.WATCH)
                    }
                    val korScore = editTextKorScore.text.toString().toInt()


                    students.add(Student(name, age, sex, hobbies, korScore))

                    editTextName.setText("")
                    editTextAge.setText("")
                    radioGroupSex.clearCheck()
                    checkBoxGame.isChecked = false
                    checkBoxSoccer.isChecked = false
                    checkBoxWatch.isChecked = false
                    editTextKorScore.setText("")

                    editTextName.requestFocus()
                    false
                }
            }

            buttonPrint.setOnClickListener {
                for(student in students){
                    textView6.append("${student.toString()}\n")
                }
            }

        }
    }
}

data class Student(
    val name: String,
    val age: Int,
    val sex: Sex,
    val hobbies: List<Hobby>,
    val korScore: Int
)

enum class Sex {
    FEMALE, MALE, DEFAULT
}

enum class Hobby {
    GAME, SOCCER, WATCH
}