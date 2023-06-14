package com.test.android33_customadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.test.android33_customadapter.databinding.ActivityMainBinding
import com.test.android33_customadapter.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activitiyMainBinding: ActivityMainBinding

    val data1 = arrayOf(
        "데이터1", "데이터2", "데이터3", "데이터4", "데이터5"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        activitiyMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activitiyMainBinding.root)

        activitiyMainBinding.run {
            listView.run {
                adapter = CustomAdapter()
            }
        }
    }


    //BaseAdapter 상속
    inner class CustomAdapter : BaseAdapter() {
        //리스트 뷰의 항목의 개수를 결정하는 메서드
        //이 메서드가 반환하는 정수 만큼 row(항목) 생성
        override fun getCount(): Int {
            return data1.size
        }

        //현재 번째의 항목(row) 뷰를 반환한다.
        override fun getItem(p0: Int): Any? {
            return null

        }

        //현재 번째의 항목(row) 뷰의 ID를 반환한다.
        override fun getItemId(p0: Int): Long {
            return 0
        }

        //row로 사용할 View를 생성해서 반환하는 메서드
        //여기서 반환하는 view를 현재 row려 활용
        //position : 구성하고자 하는 row의 순서값(0부터)
        // convertView: 재사용가능한 view가 있다면 매개변수로 들어옴
        // 스크롤 하면서 보이게 되거나 보이지 않게 되는 부분 -> 재사용되면서 보이게 됨

        //데이터가 45개인 경우 당장 화면에는 10개까지만 나올 수 있어서
        //10개의 뷰만 만듦 -> getView메서드 10번 호출, 풀은 비어있음(convertView는 null)
        //스크롤 해서 11번째 뷰로 내려가고 1번째 뷰가 안보이게 되면 -> 1번째 뷰를 풀에 넣어놈(convertView에 저장해놈)
        //또 새로운 뷰가 보이게 되면 -> 풀에 있던 뷰를 가져와서 재사용함(convertView는 null이 아니게 되고, 얘를 활용함)

        //각 row마다 viewBinding 객체를 만들어서 row의 view에 접근
        //view에는 tag가 있어서 각 view에 대한 viewBinding을 tag에 저장
        //재사용 할 view 안의 tag로 viewBinding 객체를 가져와서 해당 뷰(재사용할 뷰)에 접근
        //이 viewBinding 객체로 새로운 뷰에 맞는 새로운 데이터를 세팅해줌
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            // layout binding 객체를 담을 변수
            var rowBinding: RowBinding? = null
            // 항목 View를 담을 변수
            var mainView = convertView

            // 재사용 가능한 뷰가 없다면..
            if (mainView == null) {
                // ViewBinding 객체를 생성한다.
                rowBinding = RowBinding.inflate(layoutInflater)
                mainView = rowBinding.root

                // ViewBnding 객체를 view에 저장
                mainView!!.tag = rowBinding
            } // 재사용 가능한 뷰가 있다면
            else {
                // View에 저장돼있는 viewbinding 객체 추출
                rowBinding = mainView!!.tag as RowBinding
            }

            rowBinding.run{
                textViewRow1.text = data1[position]

                //button에 항목 순서값을 저장
                buttonRow1.setOnClickListener {
                    activitiyMainBinding.textViewResult.text = "$position : 버튼1"
                }
                buttonRow2.setOnClickListener {
                    activitiyMainBinding.textViewResult.text = "$position : 버튼2"
                }
            }



            return mainView
        }
    }
}