package com.test.android80_miniproject03.shop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.android80_miniproject03.databinding.FragmentHomeBinding
import com.test.android80_miniproject03.shop.ShopActivity

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var shopActivity: ShopActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        shopActivity = activity as ShopActivity
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
}