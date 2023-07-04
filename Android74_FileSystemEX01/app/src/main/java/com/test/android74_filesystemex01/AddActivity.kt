package com.test.android74_filesystemex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.test.android74_filesystemex01.databinding.ActivityAddBinding
import java.io.ObjectOutputStream

class AddActivity : AppCompatActivity() {
    lateinit var activityAddBinding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityAddBinding.root)

        activityAddBinding.run {
            toolbar2.run {
                setOnMenuItemClickListener {
                    if(it.itemId == R.id.action_add_complete) {
                        //내부 저장소에 person저장
                        Log.d("where", filesDir.toString())
                        val fos = openFileOutput("data1.dat", MODE_APPEND)
                        val oos = ObjectOutputStream(fos)

                        oos.writeObject(Person(
                            editTextName.text.toString(),
                            editTextAge.text.toString().toInt(),
                            editTextKor.text.toString().toInt()
                        ))

                        oos.flush()
                        oos.close()
                        fos.close()

                        Toast.makeText(this@AddActivity, "내부 저장소 쓰기 완료", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    false
                }
            }
        }
    }
}