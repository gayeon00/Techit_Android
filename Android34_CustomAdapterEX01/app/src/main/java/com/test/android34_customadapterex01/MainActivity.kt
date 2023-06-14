package com.test.android34_customadapterex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.test.android34_customadapterex01.databinding.ActivityMainBinding
import com.test.android34_customadapterex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val data1 = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            listView.adapter = CustomAdapter()

            editTextText.run{
                setOnEditorActionListener { textView, i, keyEvent ->
                    data1.add(text.toString())

                    setText("")

                    false
                }
            }
        }
    }

    inner class CustomAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return data1.size
        }

        override fun getItem(p0: Int): Any? {
            return null
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowBinding: RowBinding?
            var conView = convertView

            if(conView==null){
                rowBinding = RowBinding.inflate(layoutInflater)
                conView = rowBinding.root

                conView.tag = rowBinding
            } else {
                rowBinding = conView.tag as RowBinding
            }

            rowBinding.run {
                textViewRow.text = data1[position]

                buttonRow.setOnClickListener {
                    data1.removeAt(position)

                    this@CustomAdapter.notifyDataSetChanged()
                }
            }

            return conView
        }

    }
}