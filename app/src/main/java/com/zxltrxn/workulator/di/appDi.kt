package com.zxltrxn.workulator.di

import com.zxltrxn.workulator.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel<MainViewModel>{
        MainViewModel(
        setTask = get(),
        editTask = get(),
        getTasks = get(),
        getTaskStatus = get(),
        getStatsForTasks = get(),
        setEvent = get()
        )
    }
}