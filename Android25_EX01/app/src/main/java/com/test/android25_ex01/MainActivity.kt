package com.test.android25_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.test.android25_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val movieList = mutableListOf<Movie>()
        activityMainBinding.run {
            editTextDirectorName.setOnEditorActionListener { textView, i, keyEvent ->
                val movie = Movie(
                    editTextMovieTitle.text.toString(),
                    seekBar.progress+5000,
                    when (chipGroupGrade.checkedChipId) {
                        R.id.chipEvery -> Grade.EVERY

                        R.id.chipTwelve -> Grade.TWELVE

                        R.id.chipFifteen -> Grade.FIFTEEN

                        R.id.chipAdult -> Grade.ADULT

                        else -> Grade.DEFAULT
                    },
                    ratingBar.rating,
                    editTextDirectorName.text.toString()
                    )

                movieList.add(movie)

                editTextMovieTitle.setText("")
                seekBar.progress = 0
                chipGroupGrade.clearCheck()
                ratingBar.rating = 0f
                editTextDirectorName.setText("")

                editTextMovieTitle.requestFocus()
                true
            }

            buttonPrintMovieInfo.setOnClickListener {

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                if(currentFocus!=null){
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    currentFocus!!.clearFocus()
                }

                for (movie in movieList){
                    textViewPrintMovieInfo.append(movie.printMovieInfo())
                }
            }
        }
    }

    data class Movie(
        val title: String,
        val price: Int,
        val grade: Grade,
        val rating: Float,
        val directorName: String
    ){
        fun printMovieInfo(): String{
            return "영화 제목 :$title\n" +
                    "요금 : ${price}원\n" +
                    "관람 등급 : ${grade.name}\n" +
                    "별점 : ${rating}점\n" +
                    "감독 이름 : $directorName\n"
        }
    }

    enum class Grade(name: String) {
        EVERY("전체"), TWELVE("12세"), FIFTEEN("15세"), ADULT("성인"), DEFAULT("기본")
    }
}