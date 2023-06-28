package com.test.android62_actionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnActionExpandListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.test.android62_actionview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val data1 = arrayOf(
        "aaaa", "bbbb", "cccc", "aabb", "ccdd"
    )
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            listView.run {
                adapter = ArrayAdapter<String>(
                    this@MainActivity, android.R.layout.simple_list_item_1, data1
                )

                //리스트뷰가 검색이 가능하도록 설정
                isTextFilterEnabled = true

                setOnItemClickListener { adapterView, view, i, l ->
                    // 리스트뷰에서 position 번째 문자열 가져옴
                    val string = adapterView.adapter.getItem(i) as String
                    val idx = data1.indexOf(string)
                    activityMainBinding.textView3.text = data1[idx]


                }
            }


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.item1)
        val searchView = item?.actionView as SearchView

        searchView.queryHint = "검색어 입력"

        //ActionView가 펼쳐지거나 접혔을 때
        //true여야 펼처지고 접힙 (펼치고 접히는 동작 이외의 코드는 정상 동작함)
        item.setOnActionExpandListener(object : OnActionExpandListener {
            //펼쳐졌을 때
            override fun onMenuItemActionExpand(p0: MenuItem): Boolean {
                activityMainBinding.textView.text = "펼쳐졌을 때"
                return true
            }
            //접혔을 때
            override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                activityMainBinding.textView.text = "접혔을 때"
                return true
            }

        })
        //searchView의 리스너
        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            //문자열 입력이 완료됐을 때
            override fun onQueryTextSubmit(query: String?): Boolean {
                activityMainBinding.textView.text = "문자열 입력 완료"
                activityMainBinding.textView2.text = "입력 완료 : $query"
                searchView.clearFocus()

                item.collapseActionView()
                return true
            }

            //문자열 입력즁
            override fun onQueryTextChange(newText: String?): Boolean {
                activityMainBinding.textView.text = "문자열 입력중"
                activityMainBinding.textView2.text = "입력 중 : $newText"

                //listView에 검색어 설정
                activityMainBinding.listView.setFilterText(newText)
                if(newText?.length == 0){
                    activityMainBinding.listView.clearTextFilter()
                }

                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}