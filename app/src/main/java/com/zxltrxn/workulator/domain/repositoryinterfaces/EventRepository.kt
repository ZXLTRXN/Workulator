package com.zxltrxn.workulator.domain.repositoryinterfaces

import com.zxltrxn.workulator.domain.models.EventModel
import com.zxltrxn.workulator.domain.models.TaskModel

interface EventRepository {
    suspend fun createEvent(event: EventModel)
}