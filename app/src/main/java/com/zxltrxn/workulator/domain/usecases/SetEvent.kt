package com.zxltrxn.workulator.domain.usecases


import com.zxltrxn.workulator.domain.models.EventModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.EventRepository
import java.time.LocalDate
import java.time.temporal.ChronoField
import java.time.temporal.WeekFields
import java.util.*


class SetEvent(private val eventRepo: EventRepository) {
    fun execute(event: EventModel):Boolean {
//        val date: LocalDate = LocalDate.now()
//        val date = LocalDate.of(year, month, day)
        event.week = event.date.get(WeekFields.of(Locale.getDefault()).weekOfYear())
        return eventRepo.createEvent(event)
    }

}
