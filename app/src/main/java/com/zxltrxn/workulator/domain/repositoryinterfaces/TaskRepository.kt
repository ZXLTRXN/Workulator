package com.zxltrxn.workulator.domain.repositoryinterfaces

import com.zxltrxn.workulator.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun createTask(task:TaskModel):Boolean
    fun updateTask(newTask:TaskModel):Boolean
    fun getTaskIds(): Flow<List<UInt>>
}