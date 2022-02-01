package com.zxltrxn.workulator.data.repositories

import com.zxltrxn.workulator.data.storage.TaskEventDao
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.utils.toTask
import kotlinx.coroutines.flow.StateFlow

class TaskRepositoryImpl(private val storage: TaskEventDao): TaskRepository {

    override fun createTask(task: TaskModel) = storage.insertTask(task.toTask())

    override fun updateTask(newTask: TaskModel) = storage.updateTask(newTask.toTask())

    override fun getTaskIds(): StateFlow<List<UInt>> = storage.getTaskIds()
}