package com.test.android99_jsonex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android99_jsonex01.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    private val serverAddress = "https://a.4cdn.org/boards.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                //url 객체 만들기
                thread {
                    val url = URL(serverAddress)
                    //접속 후 스트림 추출
                    val httpURLConnection = url.openConnection() as HttpURLConnection

                    val inputStreamReader =
                        InputStreamReader(httpURLConnection.inputStream, "UTF-8")
                    //라인 단위로 한번에 많이 읽어오기 위해서 사용!
                    val bufferedReader = BufferedReader(inputStreamReader)

                    var str: String? = null
                    val stringBuffer = StringBuffer()
                    //문서의 마지막까지 읽어오기
                    do {
                        str = bufferedReader.readLine()
                        if (str != null) {
                            stringBuffer.append(str)
                        }
                    } while (str != null)

                    val data = stringBuffer.toString()

                    val root = JSONObject(data)
                    val boardsArray = root.getJSONArray("boards")

                    runOnUiThread { textView.text = "" }

                    for (idx in 0 until boardsArray.length()) {
                        val board = boardsArray.getJSONObject(idx)

                        val keys = board.keys()
                        keys.forEach {
                            if(it == "cooldowns"){
                                runOnUiThread {
                                    textView.append("$it :\n")
                                }
                                val cooldownsObject = board.getJSONObject(it)
                                val cooldownsKeys = cooldownsObject.keys()
                                cooldownsKeys.forEach { key ->
                                    runOnUiThread {
                                        textView.append("    - $key : ${cooldownsObject[key]}\n")
                                    }
                                }
                            } else {
                                runOnUiThread {
                                    textView.append("$it : ${board[it]}\n")
                                }
                            }

                        }
                        runOnUiThread {
                            textView.append("\n")
                        }
                    }
                }
            }
        }
    }
}