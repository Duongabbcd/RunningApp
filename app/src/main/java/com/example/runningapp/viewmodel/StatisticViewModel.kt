package com.example.runningapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.runningapp.local.repository.RunningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(
    @ApplicationContext private val contexts: Context,
    private val repository: RunningRepository,
//    private val analyticsLogger: AnalyticsLogger
) : ViewModel() {
}