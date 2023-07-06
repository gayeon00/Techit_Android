package com.test.android78_miniproject01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.android78_miniproject01.MainActivity
import com.test.android78_miniproject01.Memo
import com.test.android78_miniproject01.R
import com.test.android78_miniproject01.databinding.FragmentEditBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class EditFragment : Fragment() {
    lateinit var fragmentEditBinding: FragmentEditBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentEditBinding = FragmentEditBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        val memoList = mainActivity.getAllMemo()
        val memo = memoList[mainActivity.rowPosition]

        fragmentEditBinding.run {
            toolbarEdit.run {
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.EDIT_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    if (it.itemId == R.id.itemEditSave) {
                        //저장하기
                        val name = editTextTitle.text.toString()
                        val content = editTextContent.text.toString()

                        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
                        //현재시간 갖고있는 데이터
                        val now = sdf.format(Date())
                        val newMemo = Memo(name, content, now)
                        mainActivity.updateData(oldMemo = memo, newMemo = newMemo)

                        mainActivity.removeFragment(MainActivity.EDIT_FRAGMENT)
                    }
                    false
                }
            }
        }
        // Inflate the layout for this fragment
        return fragmentEditBinding.root
    }
}