package com.zxltrxn.workulator.domain.usecases


import com.zxltrxn.workulator.domain.models.EventModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository
import com.zxltrxn.workulator.utils.getWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*


class SetEvent(private val eventRepo: EventRepository) {
    suspend operator fun invoke(event: EventModel) {
        eventRepo.createEvent(event)
    }
}
