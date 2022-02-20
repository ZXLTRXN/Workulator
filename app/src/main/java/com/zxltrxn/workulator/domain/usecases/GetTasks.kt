package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class GetTasks(private val taskRepo: TaskRepository) {

    suspend operator fun invoke(): List<Long> = taskRepo.getTaskIds()
}