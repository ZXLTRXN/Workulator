package com.zxltrxn.workulator.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zxltrxn.workulator.data.storage.entities.*

@Database(entities = [Task::class,Event::class,Date::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao(): TaskEventDao
}