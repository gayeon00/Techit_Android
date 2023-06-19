package com.test.android46_ex02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.ArrayAdapter
import com.test.android46_ex02.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var activityAddBinding: ActivityAddBinding
    val dataList = arrayListOf("수박", "사과", "귤")

    override fun onCreate(savedInstanceState: Bundle?) {
        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityAddBinding.root)

        activityAddBinding.run {
            spinner.run {
                val a1 =
                    ArrayAdapter(this@AddActivity, android.R.layout.simple_spinner_item, dataList)

                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1
            }

            buttonComplete.setOnClickListener {
                val data = Fruit(
                    dataList[spinner.selectedItemPosition],
                    editTextCount.text.toString().toInt(),
                    editTextOrigin.text.toString()
                )

                //Intent로 data보내기
                val resultIntent = Intent()
                resultIntent.putExtra("fruit", data)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}

data class Fruit(val name: String?, val count: Int, val origin: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(count)
        parcel.writeString(origin)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fruit> {
        override fun createFromParcel(parcel: Parcel): Fruit {
            return Fruit(parcel)
        }

        override fun newArray(size: Int): Array<Fruit?> {
            return arrayOfNulls(size)
        }
    }

}