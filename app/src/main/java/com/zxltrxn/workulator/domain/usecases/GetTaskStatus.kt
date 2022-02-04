package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.models.TaskEventsModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskEventsRepository
import kotlinx.coroutines.flow.Flow

class GetTaskStatus(private val taskEventsRepo: TaskEventsRepository) {

    operator fun invoke(id: Long, week:Int):TaskTimeModel = taskEventsRepo.readTaskWithTime(id, week)
}