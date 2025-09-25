package com.ezt.runningapp.local.repository

import com.ezt.runningapp.local.model.Run
import com.ezt.runningapp.local.model.RunDao
import javax.inject.Inject

class RunningRepository @Inject constructor(
    val runDao: RunDao
) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistanceInMeters()

    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeedInKMH()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()


     fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

     fun getTotalDistance() = runDao.getTotalDistance()

     fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

     fun getTotalTimeInMillis() = runDao.getTotalTimeMillis()
}