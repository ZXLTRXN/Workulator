package com.zxltrxn.workulator.domain.repositoryinterfaces

import com.zxltrxn.workulator.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    fun createTask(task:TaskModel):Boolean
    fun updateTask(newTask:TaskModel):Boolean
    fun getTaskIds(): StateFlow<List<UInt>>
}