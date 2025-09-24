package com.ezt.runningapp.screen.subscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ezt.runningapp.base.BaseFragment
import com.ezt.runningapp.databinding.FragmentStatisticsBinding
import com.ezt.runningapp.viewmodel.StatisticViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>(FragmentStatisticsBinding::inflate) {
    private val statisticViewModel: StatisticViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}