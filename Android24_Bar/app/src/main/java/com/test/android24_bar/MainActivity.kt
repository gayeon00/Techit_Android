package com.test.android24_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.test.android24_bar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                //progressBar의 값을 설정한다.
                progressBar2.progress = 70

                //seekBar의 값을설정
                seekBar.progress = 70
                seekBar2.progress = 80

                //ratingBar의 값 설정
                ratingBar.rating = 1.5f
            }

            button2.setOnClickListener {
                //seekBar에 설정된 progress값을 가져와 출력
                textView.text = "SeekBar1 : ${seekBar.progress}\n"
                textView.append("SeekBar2 : ${seekBar2.progress}\n")

                //ratingBar에 설정된 별점을출력
                textView.append("RatingBar : ${ratingBar.rating}\n")
            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    //progress: 새롭게 설정된 값
                    //fromUser: 사용자가 변경한 것인지 여부
                    textView2.text = "${progress}\n"
                    if (fromUser) {
                        textView2.append("사용자에 의해 변경되었습니다.\n")
                    } else {
                        textView2.append("코드를 통해 변경되었습니다.\n")
                    }

                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }

            })

            //rating: 설정된 별점 값
            //fromUser : 사용자에 의해 설정되었는지
            ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                textView2.text = "Rating : $rating\n"
                if (fromUser) {
                    textView2.append("rating이 사용자에 의해 변경되었습니다\n")
                } else {
                    textView2.append("rating이 코드를 통해 변경되었습니다.\n")
                }
            }
        }
    }
}