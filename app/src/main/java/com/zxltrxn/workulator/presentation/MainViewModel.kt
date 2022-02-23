package com.zxltrxn.workulator.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxltrxn.workulator.domain.models.*
import com.zxltrxn.workulator.domain.usecases.*
import com.zxltrxn.workulator.utils.Constants.DEFAULT_TASK_INDEX
import com.zxltrxn.workulator.utils.Constants.TAG
import com.zxltrxn.workulator.utils.getWeek
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate


class MainViewModel(private val setTask: SetTask,
                    private val editTask: EditTask,
                    private val deleteTask: DeleteTask,
                    private val getTasks: GetTasks,
                    private val getTasksStatus: GetTasksStatus,
                    private val getStatsForTasks: GetStatisticsForTasks,
                    private val setEvent: SetEvent) :ViewModel(){


    private val _uiState = mutableStateOf(UIState(isLoading = true))
    var index = 0
    val uiState: State<UIState> = _uiState

    private val week:Int = LocalDate.now().getWeek()

    init{
        viewModelScope.launch(Dispatchers.IO){
//            _uiState.value = UIState(isLoading = true)
            val tasks = getTasksStatus(week = week)
            tasks.collect{
                withContext(Dispatchers.Main){
                    if(it.isEmpty())
                        _uiState.value = UIState(isLoading = false, items = it)
                    else _uiState.value = UIState(isLoading = false, items = it, index = index)
//                        if(uiState.value == UIState(isLoading = true))
//                            _uiState.value = UIState(isLoading = false, items = it, index = 0)
//                        else
//                            _uiState.value = UIState(isLoading = false,
//                                items = it, index = index)
                }
            }
        }
    }

    fun addTask(time:Int, name:String){
        index = 0
        viewModelScope.launch(Dispatchers.IO){
            setTask(TaskModel(name = name, targetTime = time))
        }

    }

    fun removeTask(task: TaskModel){
        index -= 1
        viewModelScope.launch(Dispatchers.IO){
            deleteTask(task)
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

//    fun nextTask(){
//        if(_uiState.value.index == _uiState.value.items.size - 1) return
//        else _uiState.value = _uiState.value.copy(index = _uiState.value.index + 1)
//
//    }
//
//    fun previousTask(){
//        if(_uiState.value.index == DEFAULT_TASK_INDEX) return
//        else _uiState.value = _uiState.value.copy(index = _uiState.value.index - 1)
//    }

    fun nextTask(){
        if(index == _uiState.value.items.size - 1) return
        else {
            index++
            _uiState.value = _uiState.value.copy(index = index)
        }

    }

    fun previousTask(){
        if(index == DEFAULT_TASK_INDEX) return
        else {
            index--
            _uiState.value = _uiState.value.copy(index = index)
        }
    }

}