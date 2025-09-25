package com.ezt.runningapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ezt.runningapp.local.repository.RunningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(
    @ApplicationContext private val contexts: Context,
    private val mainRepository: RunningRepository,
//    private val analyticsLogger: AnalyticsLogger
) : ViewModel() {

    val totalTimeRun = mainRepository.getTotalTimeInMillis()
    val totalDistance = mainRepository.getTotalDistance()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()

    val runSortedByDate = mainRepository.getAllRunsSortedByDate()
}