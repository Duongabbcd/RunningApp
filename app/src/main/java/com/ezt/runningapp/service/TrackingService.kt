package com.ezt.runningapp.service

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import com.ezt.runningapp.utils.Constants.ACTION_PAUSE_SERVICE
import com.ezt.runningapp.utils.Constants.ACTION_START_OR_RESUME_SERVICE
import com.ezt.runningapp.utils.Constants.ACTION_STOP_SERVICE


class TrackingService : LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    Log.d("TrackingService", "Start or resume service")
                }

                ACTION_PAUSE_SERVICE -> {
                    Log.d("TrackingService", "Pause service")
                }

                ACTION_STOP_SERVICE -> {
                    Log.d("TrackingService", "Stop service")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)

    }


}