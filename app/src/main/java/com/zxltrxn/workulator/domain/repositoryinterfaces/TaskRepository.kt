package com.zxltrxn.workulator.domain.repositoryinterfaces

import com.zxltrxn.workulator.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    fun createTask(task:TaskModel)
    fun updateTask(newTask:TaskModel)
    fun getTaskIds(): StateFlow<List<Long>>
}