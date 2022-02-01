package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import com.zxltrxn.workulator.domain.models.TaskModel

class SetTask(private val taskRepo: TaskRepository) {
    operator fun invoke(task:TaskModel) = taskRepo.createTask(task)
}