package com.zxltrxn.workulator.di

import com.zxltrxn.workulator.domain.usecases.*
import org.koin.dsl.module

val domainModule = module{

    factory<SetTask>{
        SetTask(taskRepo = get())
    }

    factory<EditTask>{
        EditTask(taskRepo = get())
    }

    factory<GetTasks>{
        GetTasks(taskRepo = get())
    }

    factory<SetEvent>{
        SetEvent(eventRepo = get())
    }

    factory<GetTasksStatus>{
        GetTasksStatus(taskEventsRepo = get())
    }

    factory<GetStatisticsForTasks>{
        GetStatisticsForTasks(taskEventsRepo = get())
    }
}