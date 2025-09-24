package com.ezt.runningapp.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ezt.runningapp.local.Converters
import com.ezt.runningapp.local.model.Run
import com.ezt.runningapp.local.model.RunDao

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase : RoomDatabase(){
    abstract fun getRunDao(): RunDao

}