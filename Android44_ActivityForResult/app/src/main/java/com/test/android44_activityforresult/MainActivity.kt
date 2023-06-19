package com.test.android44_activityforresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android44_activityforresult.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    // Activity를 구분하기 위해 사용하는 값
    val SECOND_ACTIVITY = 0
    val THIRD_ACTIVITY = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                //SecondActivity 실행
                val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                // startActivity(secondIntent)

                //값 설정
                secondIntent.putExtra("data1", 100)
                secondIntent.putExtra("data2", 11.11)
                secondIntent.putExtra("data3", true)
                secondIntent.putExtra("data4", "안녕")

                //Parcelable 상속받는 객체를 Intent로 넘겨주기
                val t1 = TestClass()
                t1.name = "홍길동"
                t1.age = 100
                secondIntent.putExtra("data5", t1)

                //requestCode는 사실 아무거나 넣어도됨 하지만 나중에 onActivityResult에서 requestCode로 어떤 액티비티 갔다가 돌아온건지 분기 하기 때문에 각각 다른 숫자로 하자
                startActivityForResult(secondIntent, SECOND_ACTIVITY)
            }

            button2.setOnClickListener {
                //ThirdActivity 실행
                val thirdIntent = Intent(this@MainActivity, ThirdActivity::class.java)
                // startActivity(thirdIntent)
                startActivityForResult(thirdIntent, THIRD_ACTIVITY)
            }

            //contract 객체 생성 - 나는 이걸로 멀 할거야~~ 설정
            //나는 다른 Activity를 갔다가 돌아왔을 때 행동을 설정할거야~~
            //contract 객체에 따라 동작이 달라짐
            val c1 = ActivityResultContracts.StartActivityForResult()
            val fourthActivityLauncher = registerForActivityResult(c1) {
                //fourthActivity 갔다 왔을 때 무슨 행동?
                textView.text = "FourthActivity갔다옴"

                //resultCode 로 분기
                if (it.resultCode == RESULT_OK) {
                    //intent 통해 값 추출
                    val value1 = it.data?.getIntExtra("value1", 0)
                    val value2 = it.data?.getDoubleExtra("value2", 0.0)

                    textView.append("value1 : $value1\n")
                    textView.append("value2 : $value2\n")
                }
            }

            button3.setOnClickListener {
                val fourthIntent = Intent(this@MainActivity, FourthActivity::class.java)

                //값 설정
                fourthIntent.putExtra("data1", 100)
                fourthIntent.putExtra("data2", 11.11)

                fourthActivityLauncher.launch(fourthIntent)
            }


            val c2 = ActivityResultContracts.StartActivityForResult()
            val fifthActivityLauncher = registerForActivityResult(c2) {
                textView.text = "FifthActivity갔다옴"
            }

            button4.setOnClickListener {
                val fifthIntent = Intent(this@MainActivity, FifthActivity::class.java)
                fifthActivityLauncher.launch(fifthIntent)
            }
        }
    }

    // startActivityForResult 메서드로 다른 Activity를 실행하고 다시 돌아왔을 때 자동으로 호출되는 메서드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // requestCode로 어떤 Activity를 갔다 왔는지 구분한다.
        when (requestCode) {
            SECOND_ACTIVITY -> {
                activityMainBinding.textView.text = "SecondActivity에서 돌아왔습니다\n"
                //resultCode로 작업의 결과 구분
                when (resultCode) {
                    RESULT_OK -> {
                        activityMainBinding.textView.append("작업 성공\n")

                        // 3번째 매개변수로 전달되는 Intent 객체로 부터 데이터를 추출해 사용한다.
                        if (data != null) {
                            val value1 = data.getIntExtra("value1", 0)
                            val value2 = data.getDoubleExtra("value2", 0.0)
                            val value3 = data.getBooleanExtra("value3", false)
                            val value4 = data.getStringExtra("value4")

                            activityMainBinding.textView.append("value1 : ${value1}\n")
                            activityMainBinding.textView.append("value2 : ${value2}\n")
                            activityMainBinding.textView.append("value3 : ${value3}\n")
                            activityMainBinding.textView.append("value4 : ${value4}\n")
                        }
                    }
                    //back버튼 누르면 이거!
                    RESULT_CANCELED -> {
                        activityMainBinding.textView.append("작업 취소\n")
                    }
                }
            }

            THIRD_ACTIVITY -> {
                activityMainBinding.textView.text = "ThirdActivity에서 돌아왔습니다\n"
            }
        }
    }
}

//Parcelable
//안드로이드에서 4대 구성요소 간의 객체를 전달하기 위한 직렬화를 수행
class TestClass() : Parcelable {
    lateinit var name: String
    var age: Int = 0

    //얘가 중요!!
    //intent에 parcel객체가 담기는 거임!
    constructor(parcel: Parcel) : this() {
        // 멤버 변수에 값을 담는다.
        // Parcel에 저장한 순서대로 추출한다.
        name = parcel.readString()!!
        age = parcel.readInt()
    }

    // 데이터를 Parcel 객체로 저장
    // 객체를 Intent에 넣으려고 할 때 호출된다. - Intent에 Parcel담김
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestClass> {
        //Parcel로부터 데이터 가져와서 객체 만듦
        //새로운 객체를 생성하고, parcel에 저장된값을 객체 멤버변수에 담기
        override fun createFromParcel(parcel: Parcel): TestClass {
            return TestClass(parcel)
        }

        override fun newArray(size: Int): Array<TestClass?> {
            return arrayOfNulls(size)
        }
    }

}