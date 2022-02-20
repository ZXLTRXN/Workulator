package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.models.TaskEventsModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskEventsRepository
import kotlinx.coroutines.flow.Flow

class GetTasksStatus(private val taskEventsRepo: TaskEventsRepository) {

    suspend operator fun invoke(week:Int):Flow<List<TaskTimeModel>> =
        taskEventsRepo.readTasksWithTime(week)
}