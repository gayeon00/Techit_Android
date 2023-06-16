package com.test.android40_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import com.test.android40_menu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val dataList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            // textView에 컨텍스트 메뉴를 등록한다.
            registerForContextMenu(textView)
            //ListView에 컨텍스트 메뉴 등록
            registerForContextMenu(listView)


            button.setOnClickListener {
                // 팝업 메뉴 객체를 생성한다. -> 원하는 곳을 누르면 나오게 할 수 있음
                // 제일 상단에 있는 TextView에 나타날 메뉴이다
                val pop = PopupMenu(this@MainActivity, textView)

                // 메뉴를 구성한다.
                menuInflater.inflate(R.menu.popup_menu, pop.menu)

                // 팝업메뉴를  띄운다.
                pop.show()

                //팝업메뉴를 눌렀을 때 동작하는 리스너
                pop.setOnMenuItemClickListener {
                    //메뉴의 id로 분기한다
                    textView.text = when (it.itemId) {
                        R.id.popup1 -> "메뉴항목1"
                        R.id.popup2 -> "메뉴항목2"
                        R.id.popup3 -> "하위 메뉴3-1"
                        else -> "기본값"
                    }
                    true
                }
            }

            listView.run {
                adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, dataList)
            }
        }



    }

    //옵션 메뉴를 구성하기 위해 사용하는 메서드
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //xml파일로부터 메뉴를 생성 => menuInflater가 함!
//        menuInflater.inflate(R.menu.main_menu, menu)

        // 코드를 통한 메뉴 구성
        menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴 1")
        menu?.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "코드 메뉴 2")

        val subMenu = menu?.addSubMenu("코드 메뉴 3")
        subMenu?.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "하위 메뉴 3-1")
        subMenu?.add(Menu.NONE, Menu.FIRST + 3, Menu.NONE, "하위 메뉴 3-2")

        menu?.add(Menu.NONE, Menu.FIRST + 4, Menu.NONE, "코드 메뉴 4")

        //true를 반환해야 메뉴가 나타났었음 ㅠㅠㅠㅠ(지금은 false해도 나옴)
        return true
    }

    //옵션 메뉴에서 메뉴 항목을 선택하면 호출되는 메서드
    // 매개변수로 사용자가 선택한 메뉴항목 객체가 들어옴
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activityMainBinding.run {

//            //메뉴 항목의 id로 분기
//            textView.text = when (item.itemId) {
//                R.id.menu_item1 -> "메뉴항목1"
//                R.id.menu_item2 -> "메뉴항목2"
//                R.id.menu_item31 -> "하위 메뉴3-1"
//                R.id.menu_item32 -> "하위 메뉴3-2"
//                R.id.menu_item4 -> "메뉴항목4"
//                else -> "기본값"
//            }

            textView.text = when (item.itemId) {
                Menu.FIRST -> "메뉴항목1"
                Menu.FIRST + 1 -> "메뉴항목2"
                Menu.FIRST + 2 -> "하위 메뉴3-1"
                Menu.FIRST + 3 -> "하위 메뉴3-2"
                Menu.FIRST + 4 -> "메뉴항목4"
                else -> "기본값"
            }


        }
        //메뉴 항목의 id로 분기해서 처리하자

        return super.onOptionsItemSelected(item)
    }

    // 두 번째 매개 변수(v) : 사용자가 길게 누른 뷰 객체가 들어온다.
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        //View의아이디로 분기
        when(v?.id) {
            R.id.textView -> {
                menu?.setHeaderTitle("텍스트뷰의 메뉴")
                menuInflater.inflate(R.menu.context_menu, menu)
            }
            //listView
            R.id.listView -> {
                // menuInfo 매개변수로 들어오는 객체로부터
                // 사용자가 길게 누른 항목이 몇번째 인지 파악
                //listView의 경우 ContextMenuInfo을 상속받은 AdapterContextMenuInfo클래스 타입이 들어와서 형변환해줘야
                val info = menuInfo as AdapterView.AdapterContextMenuInfo
                menu?.setHeaderTitle("${info.position} 항목의 메뉴")
                menuInflater.inflate(R.menu.list_menu, menu)
            }
        }
    }

    //컨텍스트 메뉴의 항목을 터치햇을 때 호출되는 메서드
    //길게 누른 뷰가 뭔지 구분할 방법이 없다.
    //이에 서로 다른 뷰의 컨텍스트 메뉴라고 하더라도 메뉴의 아이디는 다 다르게 구성해줘야한다.
    //ex)TextView 하위 item아이디, button하위 item아이디 다다르게
    override fun onContextItemSelected(item: MenuItem): Boolean {

        activityMainBinding.run {

            textView.text = when(item.itemId) {
                //textview의 컨텍스트 메뉴
                R.id.context1 -> "텍스트뷰 - 메뉴1"
                R.id.context2 -> "텍스트뷰 - 메뉴2"
                R.id.context3 -> "텍스트뷰 - 메뉴3"

                //listView의 컨텍스트 메뉴
                R.id.list_menu1 -> {
                    //listView의 몇번째 항목인지 정보를 갖고있음
                    val info = item.menuInfo as AdapterContextMenuInfo
                    "리스트뷰 - ${dataList[info.position]}의 메뉴1"
                }
                R.id.list_menu2 -> {
                    val info = item.menuInfo as AdapterContextMenuInfo
                    "리스트뷰 - ${dataList[info.position]}의 메뉴2"
                }
                R.id.list_menu3 -> {
                    val info = item.menuInfo as AdapterContextMenuInfo
                    "리스트뷰 - ${dataList[info.position]}의 메뉴3"
                }
                else -> "기본값"
            }
        }

        return super.onContextItemSelected(item)
    }
}