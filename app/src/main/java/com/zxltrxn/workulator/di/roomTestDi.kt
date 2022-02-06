package com.zxltrxn.workulator.di

import androidx.room.Room
import com.zxltrxn.workulator.data.storage.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomTestModule = module {
    single {
        // In-Memory database config
        Room.inMemoryDatabaseBuilder(
            androidApplication(),
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

//    single{
//        val database = get<AppDatabase>()
//        database.getDao()
//    }
}