package com.ezt.runningapp.viewmodel

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezt.runningapp.local.model.Run
import com.ezt.runningapp.local.repository.RunningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RunningViewModel @Inject constructor(
    @ApplicationContext private val contexts: Context,
    private val mainRepository: RunningRepository,
//    private val analyticsLogger: AnalyticsLogger
) : ViewModel() {

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
    val runsSortedByTimeInMillis = mainRepository.getAllRunsSortedByTimeInMillis()
    val runsSortedByCaloriesBurned = mainRepository.getAllRunsSortedByCaloriesBurned()
    val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()
    val runsSortedByAvgSpeed = mainRepository.getAllRunsSortedByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()

    val sortType = SortType.DATE

    init {
        runs.addSource(runsSortedByDate) { result ->
            if (sortType == SortType.DATE) {
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByAvgSpeed) { result ->
            if (sortType == SortType.AVG_SPEED) {
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByCaloriesBurned) { result ->
            if (sortType == SortType.CALORIES_BURNED) {
                result?.let { runs.value = it }
            }
        }


        runs.addSource(runsSortedByDistance) { result ->
            if (sortType == SortType.DISTANCE) {
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByTimeInMillis) { result ->
            if (sortType == SortType.RUNNING_TIME) {
                result?.let { runs.value = it }
            }
        }
    }

    fun insertRunningTrack(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

    fun sortRuns(sortType: SortType) = when (sortType) {
        SortType.DATE -> {
            runsSortedByDate.value?.let {runs.value = it  }
        }

        SortType.CALORIES_BURNED -> {
            runsSortedByDate.value?.let {runs.value = it  }
        }


        SortType.RUNNING_TIME -> {
            runsSortedByDate.value?.let {runs.value = it  }
        }


        SortType.DISTANCE -> {
            runsSortedByDate.value?.let {runs.value = it  }
        }


        SortType.AVG_SPEED -> {
            runsSortedByDate.value?.let {runs.value = it  }
        }

    }
}

enum class SortType {
    DATE,
    AVG_SPEED,
    CALORIES_BURNED,
    DISTANCE,
    RUNNING_TIME,

}