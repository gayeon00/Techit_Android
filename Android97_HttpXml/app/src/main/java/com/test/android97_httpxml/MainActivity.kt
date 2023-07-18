package com.test.android97_httpxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.android97_httpxml.databinding.ActivityMainBinding
import org.w3c.dom.Element
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    var serverAddress =
        "https://www.aviationweather.gov/adds/dataserver_current/httpparam?datasource=metars&requestType=retrieve&format=xml&mostRecentForEachStation=constraint&hoursBeforeNow=1.25&stationString=KDE"

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {

                thread {
                    //접속 주소를 관리하는 개체 생성
                    val url = URL(serverAddress)
                    //접속
                    val httpURLConnection = url.openConnection() as HttpURLConnection
                    // 웹 브라우저 종류를 확인할 수도 있기 때문에.... 브라우저라고 속이자!
                    httpURLConnection.addRequestProperty(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36"
                    )
                    val responseCode: Int =
                        httpURLConnection.responseCode //can call this instead of con.connect()

                    if (responseCode in 400..499) {
                        throw Exception("Bad authentication status: $responseCode") //provide a more meaningful exception message
                    } else {
                        //DOM 방식으로 xml문서를 분석할 수 있는 도구 생성
                        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
                        val documentBuilder = documentBuilderFactory.newDocumentBuilder()
                        //분석 도구를 이용해 xml문서를 분석해 각 태그들을 모두 객체로 생성한다.
                        //태그들을 관리하는 객체를 반환
                        val document = documentBuilder.parse(httpURLConnection.inputStream)

                        //최상위 태그 가져옴
                        val root = document.documentElement
                        //data 태그를 가져옴
                        val dataTagList = root.getElementsByTagName("data")
                        //METAR tag가져옴
                        val dataElement = dataTagList.item(0) as Element
                        val METARTagList = dataElement.getElementsByTagName("METAR")

                        runOnUiThread {
                            textView.text = ""
                        }

                        //태그의 수 만큼 반복
                        for (idx in 0 until METARTagList.length) {
                            //idx번째 태그 객체를 가져옴
                            val METARElement = METARTagList.item(idx) as Element

                            //METAR 태그 내에서 필요한 태그들을 가져옴
                            val rawTextList = METARElement.getElementsByTagName("raw_text")
                            val stationIdList = METARElement.getElementsByTagName("station_id")
                            val latitudeList = METARElement.getElementsByTagName("latitude")
                            val longitudeList = METARElement.getElementsByTagName("longitude")

                            val rawTextTag = rawTextList.item(0) as Element
                            val stationIdTag = stationIdList.item(0) as Element
                            val latitudeTag = latitudeList.item(0) as Element
                            val longitudeTag = longitudeList.item(0) as Element

                            //태그 내의 데이터 가져오기
                            val rawText = rawTextTag.textContent
                            val stationId = stationIdTag.textContent
                            val latitude = latitudeTag.textContent.toDouble()
                            val longitude = longitudeTag.textContent.toDouble()

                            runOnUiThread {
                                textView.append("rowText : ${rawText}\n")
                                textView.append("stationId : ${stationId}\n")
                                textView.append("latitude : ${latitude}\n")
                                textView.append("longitude : ${longitude}\n\n")
                            }
                        }
                    }
                }
            }
        }
    }
}