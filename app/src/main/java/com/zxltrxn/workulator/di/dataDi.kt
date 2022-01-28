package com.zxltrxn.workulator.di

import com.zxltrxn.workulator.data.repositories.EventRepositoryImpl
import com.zxltrxn.workulator.data.repositories.TaskEventsRepositoryImpl
import com.zxltrxn.workulator.data.repositories.TaskRepositoryImpl
import com.zxltrxn.workulator.data.storage.TaskEventStorage
import com.zxltrxn.workulator.data.storage.db.DBTaskStorage
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskEventsRepository
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import org.koin.dsl.module

val dataModule = module{
    single<TaskEventStorage>{
        DBTaskStorage(context = get())
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