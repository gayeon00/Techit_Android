package com.test.android44_activityforresult

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.test.android44_activityforresult.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var activitySecondBinding: ActivitySecondBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.run {
            //Intent로부터 데이터 추출
            textViewSecond.text = "SecondActivity\n"
            //저장된 것이 없을 경우 defaultValue사용
            val data1 = intent.getIntExtra("data1",0)
            val data2 = intent.getFloatExtra("data2", 0f)
            val data3 = intent.getBooleanExtra("data3", false)
            //기본 자료형이 아닌 string과 같은 객체는 기본값 X -> 없을 경우 null반환
            val data4 = intent.getStringExtra("data4")

            textViewSecond.append("data1 : $data1\n")
            textViewSecond.append("data2 : $data2\n")
            textViewSecond.append("data3 : $data3\n")
            textViewSecond.append("data4 : $data4\n")

            //객체복원
            val data5 = intent.getParcelableExtra<TestClass>("data5")
            textViewSecond.append("data5.name : ${data5?.name}")
            textViewSecond.append("data5.age : ${data5?.age}")

            buttonSecond.setOnClickListener {
                //작업의 결과 설정(돌아갈 때 전달할 결과가 없을 경우 - setResult)
                // -> 취소하고 돌아온 것인지, 특정 작업을 완료해서 돌아온 것인지 구분!
                //requestCode처럼 아무 정수나 넣어주면 됨
                //but 안드로이드에서 제공해주는 숫자들
                //RESULT_OK: 작업이 정상적으로 끝남
                //RESULT_CANCEL: 작업이 취소됨
                //RESULT_FIRST_USER: 작업의 상황을 추가적으로 정의하고 싶을 때(+1,+2,+3...)
                /*setResult(RESULT_OK)*/

                //이전 Activity로 전달할 데이터를 설정할 Intent 객체 생성
                //액티비티를 실행할 목적이 아니라 딱히 정보 넣을 필요 없음 (스택에서 제거할 목적이기 때문에)
                val resultIntent = Intent()
                //값 설정
                resultIntent.putExtra("value1",200)
                resultIntent.putExtra("value2",22.2)
                resultIntent.putExtra("value3",false)
                resultIntent.putExtra("value4","반갑습니다")

                // 돌아갈 때 전달 할 값이 있다면
                // setResult 메서드의 두 번째 매개변수에 Intent 객체를 넣어준다.
                setResult(RESULT_OK, resultIntent)

                //Activity 종료
                finish()
            }
        }
    }
}