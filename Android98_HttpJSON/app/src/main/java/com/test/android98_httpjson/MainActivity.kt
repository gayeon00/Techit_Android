package com.test.android98_httpjson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android98_httpjson.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val serverAddress = "https://api.nationalize.io?name=michael"

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

                    //JSON 데이터 분석
                    // {} : JSONObject, 이름 - 값 형태
                    // [] : JSONArray, 0부터 증가하는 순서값을 가지고 관리
                    // 100 : 정수
                    // 11.11 : 실수
                    // "문자열" : 문자열
                    // true, false : 논리형

                    // 최상위가 {}이므로 JSONObject 생성
                    val root = JSONObject(data)
                    val count = root.getInt("count")
                    val name = root.getString("name")

                    runOnUiThread {
                        textView.text = "count : $count\n"
                        textView.append("name : $name\n")
                    }

                    val countryArray = root.getJSONArray("country")

                    for (idx in 0 until countryArray.length()) {
                        val country = countryArray.getJSONObject(idx)

                        val countryId = country.getString("country_id")
                        val probability = country.getDouble("probability")

                        runOnUiThread {
                            textView.append("country_id : $countryId\n")
                            textView.append("probability : $probability\n\n")
                        }
                    }
                }
            }
        }
    }
}