package com.medically.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.medically.data.entity.LocalLecture

@Database(entities = [LocalLecture::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): Dao
}