package com.test.android42_activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// onCreate : Activity 처음 실행시나 화면 회전 발생시에 대한 처리
// onPause : 일시 정지되기 전에 대한 처리
// onResume : 일시 정지 되고 난 후 다시 돌아올 때의 처리
// onDestroy : Activity가 종료되기 전에 필요한 처리

class MainActivity : AppCompatActivity() {
    //화면 회전이 발생했을 때 처리도 수행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("lion", "onCreate")
    }

    // 액티비티가 처음 생성될 때 onCreate다음으로 호출
    // 액티비가 보이지 않는 상태가 되었다가 다시 보이게 될 때 onRestart 다음에 호출된다
    override fun onStart() {
        super.onStart()
        Log.d("lion", "onStart")
    }

    //액티비티ㅏㄱ 처음 생성될 때 onStart다음으로 호출
    //다른 액티비티가 보여지고 다시 이 액티비티가 보여질 때 호출
    //액티비티가 보여지지 않았다가(백그라운드에 있다가) 다시 보일 때 onStart 호출
    override fun onResume() {
        super.onResume()
        Log.d("lion", "onResume")
    }

    //다른 액티비티가 눈에 보일 때 호출
    //현재 액티비티는 일시정지
    override fun onPause() {
        super.onPause()
        Log.d("lion", "onPause")
    }

    // 현재 액티비티의 화면이 완전히 보이지 않게 될 때
    // onPause 다음에 호출된다.
    override fun onStop() {
        super.onStop()
        Log.d("lion", "onStop")
    }

    // onStop이 호출된 이후 다시 액티비티가 보여지는 상태가 될 때
    // 호출된다.
    override fun onRestart() {
        super.onRestart()
        Log.d("lion", "onRestart")
    }

    // 액티비티가 완전 종료될 때 호출
    override fun onDestroy() {
        super.onDestroy()
        Log.d("lion", "onDestroy")
    }
}