package com.zxltrxn.workulator.data.repositories

import com.zxltrxn.workulator.data.storage.TaskEventDao
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.utils.toTaskModelStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class TaskRepositoryImpl(private val storage: TaskEventDao): TaskRepository {

    override fun createTask(task: TaskModel): Boolean = storage.insertTask(task.toTaskModelStorage())

    override fun updateTask(newTask: TaskModel): Boolean = storage.updateTask(newTask.toTaskModelStorage())

    override fun getTaskIds(): StateFlow<List<UInt>> = storage.getTaskIds()
}