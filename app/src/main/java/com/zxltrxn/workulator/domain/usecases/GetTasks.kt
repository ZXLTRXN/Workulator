package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(private val taskRepo: TaskRepository) {

    fun execute(): Flow<List<UInt>> = taskRepo.getTaskIds()
}