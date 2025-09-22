package com.example.runningapp.local.repository

import com.example.runningapp.local.model.Run
import com.example.runningapp.local.model.RunDao
import javax.inject.Inject

class RunningRepository @Inject constructor(
    val runDao: RunDao
) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    suspend fun getAlRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    suspend fun getAlRunsSortedByDistance() = runDao.getAllRunsSortedByDistanceInMeters()

    suspend fun getAlRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()

    suspend fun getAlRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeedInKMH()

    suspend fun getAlRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()


    suspend fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    suspend fun getTotalDistance() = runDao.getTotalDistance()

    suspend fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    suspend fun getTotalTimeInMillis() = runDao.getTotalTimeMillis()
}