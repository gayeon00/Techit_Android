package com.test.android61_actionbar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.test.android61_actionbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
    }

    // 옵션메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item1 -> {
                activityMainBinding.textView.text = "메뉴1을 누름"
            }
            R.id.item2 -> {
                activityMainBinding.textView.text = "메뉴2을 누름"
            }
            R.id.item3 -> {
                activityMainBinding.textView.text = "메뉴3을 누름"
            }
            R.id.item4 -> {
                activityMainBinding.textView.text = "메뉴4을 누름"
            }
        }

        return super.onOptionsItemSelected(item)
    }
}