package com.test.android60_fragmentex02.fragment.input_fragment

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.test.android60_fragmentex02.FragmentName
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.R
import com.test.android60_fragmentex02.SoccerPlayer
import com.test.android60_fragmentex02.SwimPlayer
import com.test.android60_fragmentex02.databinding.DialogBinding
import com.test.android60_fragmentex02.databinding.FragmentSwimInputBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SwimInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SwimInputFragment : Fragment() {
    lateinit var fragmentSwimInputBinding: FragmentSwimInputBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSwimInputBinding = FragmentSwimInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentSwimInputBinding.run {
            buttonSWType.setOnClickListener {
                val dialogBinding = DialogBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder()
                builder.setTitle("수영 방법 입력")

                builder.setView(dialogBinding.root)

                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                    val str = dialogBinding.editTextDialog.text.toString()

                    mainActivity.studentList.add(
                        SwimPlayer(
                            editTextSWName.text.toString(),
                            str
                        )
                    )

                    Log.d("now", mainActivity.studentList.toString())

                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                }

                builder.setNegativeButton("취소", null)
                builder.show()
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swim_input, container, false)
    }

    class MyDialogFragment: DialogFragment() {

    }
}