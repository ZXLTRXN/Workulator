package com.zxltrxn.workulator.data.repositories

import com.zxltrxn.workulator.data.storage.TaskEventStorage
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.utils.toTaskModelStorage
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val storage: TaskEventStorage): TaskRepository {

    override fun createTask(task: TaskModel): Boolean = storage.insertTask(task.toTaskModelStorage())

    override fun updateTask(newTask: TaskModel): Boolean = storage.updateTask(newTask.toTaskModelStorage())

    override fun getTaskIds(): Flow<List<UInt>> = storage.getTaskIds()
}