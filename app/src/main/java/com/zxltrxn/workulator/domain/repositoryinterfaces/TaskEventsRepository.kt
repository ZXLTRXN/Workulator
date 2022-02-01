package com.zxltrxn.workulator.domain.repositoryinterfaces

import com.zxltrxn.workulator.domain.models.TaskEventsModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import kotlinx.coroutines.flow.Flow

interface TaskEventsRepository {
        fun readAllTasksWithEvents(): Flow<List<TaskEventsModel>>
        fun readTaskWithTime(id: UInt, week:Int):TaskTimeModel
}