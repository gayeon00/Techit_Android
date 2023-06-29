package com.test.android60_fragmentex02.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.android60_fragmentex02.BaseballPlayer
import com.test.android60_fragmentex02.MainActivity
import com.test.android60_fragmentex02.SoccerPlayer
import com.test.android60_fragmentex02.SwimPlayer
import com.test.android60_fragmentex02.databinding.FragmentShowBinding
import com.test.android60_fragmentex02.databinding.ShowBaseballBinding
import com.test.android60_fragmentex02.databinding.ShowSoccerBinding
import com.test.android60_fragmentex02.databinding.ShowSwimBinding

class ShowFragment : Fragment() {
    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    lateinit var baseballBinding: ShowBaseballBinding
    lateinit var soccerBinding: ShowSoccerBinding
    lateinit var swimBinding: ShowSwimBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        fragmentShowBinding.run {
            val containerLayout = fragmentShowBinding.containerLayout

            when (mainActivity.student) {
                is BaseballPlayer -> {
                    baseballBinding = ShowBaseballBinding.inflate(layoutInflater)
                    containerLayout.addView(baseballBinding.root)
                    showBaseballPlayerInfo(mainActivity.student as BaseballPlayer)
                }

                is SoccerPlayer -> {
                    soccerBinding = ShowSoccerBinding.inflate(layoutInflater)
                    containerLayout.addView(soccerBinding.root)
                    showSoccerPlayerInfo(mainActivity.student as SoccerPlayer)
                }

                is SwimPlayer -> {
                    swimBinding = ShowSwimBinding.inflate(layoutInflater)
                    containerLayout.addView(swimBinding.root)
                    showSwimPlayerInfo(mainActivity.student as SwimPlayer)
                }
            }


        }
        return fragmentShowBinding.root
    }

    private fun showBaseballPlayerInfo(player: BaseballPlayer) {
        baseballBinding.run {
            textViewBBName.text = player.name
            textViewBBBattingAvg.text = player.battingAvg.toString()
            textVieBBHCount.text = player.hCount.toString()
            textViewBBACount.text = player.aCount.toString()
        }
    }

    private fun showSoccerPlayerInfo(player: SoccerPlayer) {
        soccerBinding.run {
            textViewSCName.text = player.name
            textViewSCHCount.text = player.helpCount.toString()
            textViewSCGCount.text = player.goalCount.toString()
        }
    }

    private fun showSwimPlayerInfo(player: SwimPlayer) {
        swimBinding.run {
            textViewSWName.text = player.name
            textViewSWType.text = player.swimType
        }
    }

}