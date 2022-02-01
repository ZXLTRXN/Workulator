package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository

class EditTask(private val taskRepo: TaskRepository) {

    operator fun invoke(newTask:TaskModel) = taskRepo.updateTask(newTask)
}