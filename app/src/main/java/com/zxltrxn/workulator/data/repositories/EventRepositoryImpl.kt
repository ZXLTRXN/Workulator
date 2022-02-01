package com.zxltrxn.workulator.data.repositories

import com.zxltrxn.workulator.data.storage.TaskEventDao
import com.zxltrxn.workulator.domain.models.EventModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository

import com.zxltrxn.workulator.utils.toEventWithWeek

class EventRepositoryImpl(private val storage:TaskEventDao):EventRepository {

    override fun createEvent(event: EventModel) = storage.insertEvent(event.toEventWithWeek())
}