package com.zxltrxn.workulator.data.repositories

import com.zxltrxn.workulator.data.storage.TaskEventStorage
import com.zxltrxn.workulator.domain.models.EventModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository
import com.zxltrxn.workulator.utils.toEventModelStorage

class EventRepositoryImpl(private val storage:TaskEventStorage):EventRepository {

    override fun createEvent(event: EventModel): Boolean = storage.insertEvent(event.toEventModelStorage())
}