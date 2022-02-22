package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository

class DeleteTask(private val taskRepo: TaskRepository) {
    suspend operator fun invoke(task: TaskModel) = taskRepo.updateTask(task.copy(isActive = false))
}