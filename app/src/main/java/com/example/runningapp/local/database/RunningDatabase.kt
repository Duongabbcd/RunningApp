package com.example.runningapp.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.runningapp.local.Converters
import com.example.runningapp.local.model.Run
import com.example.runningapp.local.model.RunDao

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase : RoomDatabase(){
    abstract fun getRunDao(): RunDao

}