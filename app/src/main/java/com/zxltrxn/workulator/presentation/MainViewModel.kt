package com.zxltrxn.workulator.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zxltrxn.workulator.domain.models.*
import com.zxltrxn.workulator.domain.usecases.*
import com.zxltrxn.workulator.utils.Constants.TAG
import kotlinx.coroutines.flow.Flow


class MainViewModel(private val setTask: SetTask,
    private val editTask: EditTask,
    private val getTasks: GetTasks,
    private val getTaskStatus: GetTaskStatus,
    private val getStatsForTasks: GetStatisticsForTasks,
    private val setEvent: SetEvent) :ViewModel(){

    init{
//        setTask(TaskModel("task",12,listOf(12,52)))
//        setTask(TaskModel("task new",25,listOf(180,90)))
    }


//    private var taskIds: List<Long> = getTasks()


//
    fun getf(){
    Log.d(TAG, "getf: 2")
    }




}