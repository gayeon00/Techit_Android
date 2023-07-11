package com.test.android80_miniproject03.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.android80_miniproject03.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var fragmentSearchBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater)
        return fragmentSearchBinding.root
    }
}