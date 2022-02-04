package com.zxltrxn.workulator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zxltrxn.workulator.data.storage.TaskEventDao
import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import com.zxltrxn.workulator.domain.usecases.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow


class MainViewModel(
//    private val setTask: SetTask,
//                    private val editTask: EditTask,
//                    private val getTasks: GetTasks,
//                    private val getTaskStatus: GetTaskStatus,
//                    private val getStatsForTasks: GetStatisticsForTasks,
//                    private val setEvent: SetEvent

 val storage:TaskEventDao
                    ) :ViewModel(){


//    init{
//        setTask(TaskModel("task",12,listOf(12,52)))
//    }

//    val _taskIds: StateFlow<List<UInt>> = getTasks()
//
    fun getf() = 2




}