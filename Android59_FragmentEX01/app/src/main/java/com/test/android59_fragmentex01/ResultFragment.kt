package com.test.android59_fragmentex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android59_fragmentex01.databinding.FragmentResultBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        // Inflate the layout for this fragment

        val animal = mainActivity.animalList[mainActivity.rowPosition]

        fragmentResultBinding.run {
            textViewAnimalType.text = animal.type
            textViewName.text = animal.name
            textViewAge.text = animal.age.toString()
            textViewWeight.text = animal.weight.toString()

            buttonToMain.setOnClickListener {
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
            }

            buttonDelete.setOnClickListener {
                mainActivity.animalList.removeAt(mainActivity.rowPosition)
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
            }
        }
        return fragmentResultBinding.root
    }
}