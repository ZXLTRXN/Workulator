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


//    private var taskIds: List<Long> = getTasks()

}