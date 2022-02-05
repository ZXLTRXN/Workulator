package com.zxltrxn.workulator.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zxltrxn.workulator.data.storage.converters.PresetsConverter
import com.zxltrxn.workulator.data.storage.entities.*

@Database(entities = arrayOf(Task::class, Event::class, Date::class), version = 1,exportSchema = false)
@TypeConverters(PresetsConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao(): TaskEventDao
}