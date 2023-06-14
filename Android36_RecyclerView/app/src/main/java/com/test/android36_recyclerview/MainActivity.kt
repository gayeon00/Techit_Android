package com.test.android36_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.android36_recyclerview.databinding.ActivityMainBinding
import com.test.android36_recyclerview.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val imgRes = intArrayOf(
        R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3,
        R.drawable.imgflag4, R.drawable.imgflag5, R.drawable.imgflag6,
        R.drawable.imgflag7, R.drawable.imgflag8
    )

    val data1 = arrayOf(
        "토고", "프랑스 cnrkcnrkdfsdfsdfsdfsdfs", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            recyclerView.adapter = RecyclerAdapterClass()

            //recyclerView 항목을 어떻게 보여줄 것인가
            //위에서 아래 방향으로
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            //그리드 (한 줄에 몇칸을 사용할 것인지) - 표 느낌! (같은 행에 있는 칸은 크기가 같음)
//            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            // 각각의 칸이 다른 크기로 설정 가능 (같은 행이라도 다른 크기로 배치)
//            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    // RecyclerView의 AdapterClass
    // 1. 아무것도 상속받지 않은 RecyclerAdapterClass 클래스를 만들어준다.
    // 2. inner class로 ViewHolder를 상속받는 ViewHolderClass를 만들어준다.
    // 3. AdapterClass를 RecyclerView.Adapter를 상속하게 한다. 제네릭으로 앞서 만든 ViewHolderClass을 넣어준다 -> 오류 뜨는건 suggestion따라 imple해주면 됨
    // 4. 필요한 메서드들을 구현한다.
    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        // RecyclerView의 Row 하나가 가지고 있는 View들의 객체를 가지고 있는 Holder Class
        // 주 생성자로 ViewBinding 객체를 받는다.
        // 부모의 생성자에게 행 하나로 사용할 View를 전달한다.
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root), OnClickListener{
            //viewHolder에 onClick리스너 달아줘야함!
            var textViewRow:TextView
            var imageViewRow:ImageView

            init{
                // 사용하고자 하는 View를 변수에 담아준다.
                textViewRow = rowBinding.textViewRow
                imageViewRow = rowBinding.imageViewRow
            }

            override fun onClick(p0: View?) {
                //ViewHolder를 통해 몇번째 항목이 선택됐는지 가져옴
                activityMainBinding.textView.text = data1[adapterPosition]
            }
        }

        // ViewHolder 객체를 생성해서 반환
        // 전체 행의 개수가 아닌 필요한 만큼만 행으로 사용할 View를 만들고 ViewHolder도 생성한다. (재사용 하기 때문!)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // ViewBinding
            val rowBinding = RowBinding.inflate(layoutInflater)
            //ViewHolder
            val viewHolderClass = ViewHolderClass(rowBinding)

            //클릭 이벤트 설정
            rowBinding.root.setOnClickListener(viewHolderClass)

            //항목 View의 가로세로길이를 설정(터치 때문)
            val params = RecyclerView.LayoutParams(
                //가로 길이
                RecyclerView.LayoutParams.MATCH_PARENT,
                //세로길이
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params


            return viewHolderClass
        }


        // 전체 행의 개수 반환
        override fun getItemCount(): Int {
            return imgRes.size
        }

        //viewHolder를 통해 View에 접근하여 View의 값을 설정한다.
        //holder : viewholder 객체 -> viewHolder를 재사용하게됨
        //position: 특정 행이 몇번째 순서인지
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewRow.text = data1[position]
            holder.imageViewRow.setImageResource(imgRes[position])
        }
    }
    //ViewHolder를 반환하게 돼있음
    // Linear 아래 Image, TextView로 이루어져있다고 하면, Linear갖고있는 변수 하나,,, 등등 해서 이 세 변수를 갖고있는데 viewholder
    // (customadapter에서 썼던 viewBinding 개념이랑 비슷)
    // 사라지는 얘들이 갖고있는 정보들 중 중요한 정보를 갖고있는 역할!
    // 한 객체 내부 50개 정보중 10개만 중요 -> 10개위해 필요없는 40개를 갖고잇는건 비효율!
    // -> inner class에 10개 저장하자! 그러면 객체가 소멸돼도 10개 정보는 남아있음!

/*    리스트 뷰 어댑터 내부 동작
    어댑터가 항목뷰를 생성하고 이게 통채로 전부 풀에 담김
    보여줄 항목들은 풀에서 제거
     즉, ListView는 풀을 관리!
     리스트뷰 -> 풀 -> n번째 항목 -> 항목 내부 textView로 접근*/

/*    리사이클러뷰 어댑터 내부동작
    어댑터가 항목뷰 생성하면 풀에 전달 이때, Holder가 함께 생성됨(Holder는 항목 내 textView, EditText 들의 객체의 주소 값을 가지고 있)
     이 Holder 풀을 관리
     (항목뷰는 ㄹㅇ 화면에 뿌려주는 역할만 함)
     adapter의 n번째 홀더에 접근 -> 해당 항목의 뷰(textView 등)에 접근*/

}