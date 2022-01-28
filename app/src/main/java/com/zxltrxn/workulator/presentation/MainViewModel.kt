package com.zxltrxn.workulator.presentation

import androidx.lifecycle.ViewModel
import com.zxltrxn.workulator.domain.usecases.*


class MainViewModel(private val setTask: SetTask,
                    private val editTask: EditTask,
                    private val getTasks: GetTasks,
                    private val getTaskStatus: GetTaskStatus,
                    private val getStatsForTasks: GetStatisticsForTasks,
                    private val setEvent: SetEvent) :ViewModel(){


    fun init(){

    }
//    private val rep by lazy(LazyThreadSafetyMode.NONE){
//        repImpl(DBStorage(context))
//    }
//
//    private val UC by lazy(LazyThreadSafetyMode.NONE){
//        UC(rep)
//    }
}