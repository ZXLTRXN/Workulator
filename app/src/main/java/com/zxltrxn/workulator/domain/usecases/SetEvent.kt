package com.zxltrxn.workulator.domain.usecases


import com.zxltrxn.workulator.domain.models.EventModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository
import com.zxltrxn.workulator.utils.getWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*


class SetEvent(private val eventRepo: EventRepository) {
    operator fun invoke(event: EventModel) {
//        val date: LocalDate = LocalDate.now()
//        val date = LocalDate.of(year, month, day)

        event.week = event.date.getWeek()
        eventRepo.createEvent(event)
    }

}
