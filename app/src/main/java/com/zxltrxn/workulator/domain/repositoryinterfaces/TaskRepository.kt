package com.zxltrxn.workulator.domain.repositoryinterfaces

import com.zxltrxn.workulator.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    suspend fun createTask(task:TaskModel)
    suspend fun updateTask(newTask:TaskModel)
    suspend fun getTaskIds(): List<Long>
}