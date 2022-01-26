package com.zxltrxn.workulator.domain

import com.zxltrxn.workulator.domain.models.TaskModel

interface TaskRepository {
    fun createTask(task:TaskModel):Boolean
    fun readTasks():List<TaskModel>
    fun updateTask(newTask:TaskModel):Boolean
    fun deleteTask(id:UInt):Boolean
}