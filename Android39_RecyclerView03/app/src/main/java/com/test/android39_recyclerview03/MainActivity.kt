package com.test.android39_recyclerview03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android39_recyclerview03.databinding.ActivityMainBinding
import com.test.android39_recyclerview03.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val nationList = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    )

    val playerList = mutableListOf<Player>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            buttonRegister.setOnClickListener {
                linearLayoutRegister.visibility = View.VISIBLE
                linearLayoutShow.visibility = View.GONE
            }

            buttonShow.setOnClickListener {
                linearLayoutRegister.visibility = View.GONE
                linearLayoutShow.visibility = View.VISIBLE
            }

            //spinner 설정해주기
            ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                nationList
            ).also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = arrayAdapter
            }

            buttonAdd.setOnClickListener {
                val position = when (radioGroup.checkedRadioButtonId) {
                    R.id.radioButtonAttacker -> "공격수"
                    R.id.radioButtonMid -> "미드필더"
                    R.id.radioButtonDefender -> "수비수"
                    R.id.radioButtonGoalKeeper -> "골키퍼"
                    else -> "기본값"
                }
                val player = Player(
                    nationList[spinner.selectedItemPosition],
                    editTextName.text.toString(),
                    position
                )
                playerList.add(player)

                recyclerView.adapter?.notifyDataSetChanged()

            }

            recyclerView.run {
                adapter = RecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }


        }
    }

    inner class RecyclerAdapter : Adapter<RecyclerAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(rowBinding: RowBinding) : ViewHolder(rowBinding.root) {
            var imgViewFlag = rowBinding.imageViewFlag
            var textViewName = rowBinding.textViewName
            var textViewPosition = rowBinding.textViewPosition
        }

        //viewholder생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            // ViewBinding
            val rowBinding = RowBinding.inflate(layoutInflater)

            //항목 View의 가로세로길이를 설정(터치 때문)
            val params = RecyclerView.LayoutParams(
                //가로 길이
                RecyclerView.LayoutParams.MATCH_PARENT,
                //세로길이
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params

            return RecyclerViewHolder(rowBinding)
        }

        override fun getItemCount(): Int {
            return playerList.size
        }

        //bind해줌
        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val imgResource = when (playerList[position].nation) {
                "토고" -> R.drawable.imgflag1
                "프랑스" -> R.drawable.imgflag2
                "스위스" -> R.drawable.imgflag3
                "스페인" -> R.drawable.imgflag4
                "일본" -> R.drawable.imgflag5
                "독일" -> R.drawable.imgflag6
                "브라질" -> R.drawable.imgflag7
                "대한민국" -> R.drawable.imgflag8
                else -> 0
            }
            holder.imgViewFlag.setImageResource(imgResource)
            holder.textViewName.text = playerList[position].name
            holder.textViewPosition.text = playerList[position].position
        }
    }

    data class Player(val nation: String, val name: String, val position: String)

}