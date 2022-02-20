package com.zxltrxn.workulator.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxltrxn.workulator.domain.models.*
import com.zxltrxn.workulator.domain.usecases.*
import com.zxltrxn.workulator.utils.Constants.TAG
import com.zxltrxn.workulator.utils.getWeek
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate


class MainViewModel(private val setTask: SetTask,
                    private val editTask: EditTask,
                    private val getTasks: GetTasks,
                    private val getTasksStatus: GetTasksStatus,
                    private val getStatsForTasks: GetStatisticsForTasks,
                    private val setEvent: SetEvent) :ViewModel(){

//    private var tasks = flowOf(listOf<TaskTimeModel>())
    private val _uiState = mutableStateOf(UIState(isLoading = true))
    val uiState: State<UIState> = _uiState

    private val week:Int = LocalDate.now().getWeek()

    init{
        viewModelScope.launch(Dispatchers.IO){
//            _uiState.value = UIState(isLoading = true)
            val tasks = getTasksStatus(week = week)
            tasks.collect{
                withContext(Dispatchers.Main){
                    _uiState.value = UIState(isLoading = false, items = it)
                }
            }
        }
    }

    fun addTask(time:Int, name:String){
        viewModelScope.launch(Dispatchers.IO){
            setTask(TaskModel(name = name, targetTime = time))
        }
    }

    fun setPreset(task:TaskModel,newPreset:Int, index:Int){
        viewModelScope.launch(Dispatchers.IO){
            val newPresets = task.presets.toMutableList()
            newPresets[index] = newPreset
            editTask(task.copy(presets = newPresets))
        }
    }

    fun setEvent(id:Long, time:Int){
        viewModelScope.launch(Dispatchers.IO){
            setEvent(EventModel(date = LocalDate.now(), taskId = id,time = time, week = week))
        }
    }







}