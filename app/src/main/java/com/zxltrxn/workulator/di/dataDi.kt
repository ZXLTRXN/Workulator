package com.zxltrxn.workulator.di

import androidx.room.Room
import com.zxltrxn.workulator.data.repositories.EventRepositoryImpl
import com.zxltrxn.workulator.data.repositories.TaskEventsRepositoryImpl
import com.zxltrxn.workulator.data.repositories.TaskRepositoryImpl
import com.zxltrxn.workulator.data.storage.AppDatabase
import com.zxltrxn.workulator.data.storage.TaskEventDao
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskEventsRepository
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module{

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app-db"
        ).build()
    }

    single{
        val database = get<AppDatabase>()
        database.getDao()
    }

    single<TaskRepository>{
        TaskRepositoryImpl(storage = get())
    }

    single<EventRepository>{
        EventRepositoryImpl(storage = get())
    }

    single<TaskEventsRepository>{
        TaskEventsRepositoryImpl(storage = get())
    }
}