package com.zxltrxn.workulator.data

import com.zxltrxn.workulator.data.storage.models.TaskModelStorage
import com.zxltrxn.workulator.data.storage.TaskStorage
import com.zxltrxn.workulator.domain.TaskRepository
import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.utils.toTaskModel
import com.zxltrxn.workulator.utils.toTaskModelStorage

class TaskRepositoryImpl(private val taskStorage: TaskStorage):TaskRepository {
    override fun createTask(task: TaskModel): Boolean = taskStorage.save(task.toTaskModelStorage())

    override fun readTasks(): List<TaskModel> = taskStorage.getAll().map{it.toTaskModel()}

    override fun updateTask(newTask: TaskModel): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTask(id: UInt): Boolean {
        TODO("Not yet implemented")
    }
}