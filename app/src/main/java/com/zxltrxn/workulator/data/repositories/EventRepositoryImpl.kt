package com.zxltrxn.workulator.data.repositories

import com.zxltrxn.workulator.data.storage.TaskEventDao
import com.zxltrxn.workulator.data.storage.entities.Date
import com.zxltrxn.workulator.data.storage.entities.Event
import com.zxltrxn.workulator.domain.models.EventModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository
import com.zxltrxn.workulator.utils.toDate
import com.zxltrxn.workulator.utils.toEvent

import com.zxltrxn.workulator.utils.toEventWithWeek

class EventRepositoryImpl(private val storage:TaskEventDao):EventRepository {

    override fun createEvent(event: EventModel) =
        storage.insertEventWithWeek(date = event.toDate(),event = event.toEvent())

}