package com.zxltrxn.workulator.domain.repositoryinterfaces

import com.zxltrxn.workulator.domain.models.TaskModel

interface TaskRepository {
    fun createTask(task:TaskModel):Boolean
    fun updateTask(newTask:TaskModel):Boolean
}