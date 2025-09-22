package com.example.runningapp.screen.subscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.runningapp.base.BaseFragment
import com.example.runningapp.databinding.FragmentStatisticsBinding
import com.example.runningapp.viewmodel.StatisticViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>(FragmentStatisticsBinding::inflate) {
    private val statisticViewModel: StatisticViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}