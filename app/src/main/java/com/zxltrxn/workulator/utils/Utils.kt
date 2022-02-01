package com.zxltrxn.workulator.utils

import android.content.Context
import android.widget.Toast
import com.zxltrxn.workulator.data.models.*
import com.zxltrxn.workulator.data.storage.entities.*
import com.zxltrxn.workulator.domain.models.*
import java.time.LocalDate

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}



fun LocalDate.toLong():Long = this.toEpochDay()

fun Long.toLocalDate():LocalDate = LocalDate.ofEpochDay(this)



fun TaskModel.toTask(): Task = Task(id = this.id,name = this.name,
        target_time = this.targetTime,presets = this.presets, is_active = this.isActive)

fun Task.toTaskModel():TaskModel = TaskModel(id = this.id, name = this.name,
        targetTime = this.target_time, presets = this.presets, isActive = this.is_active)



fun EventModel.toEventWithWeek():EventWithWeek = EventWithWeek(
    Event(date = this.date.toLong(), task_id = this.taskId, time = this.time),
    week = this.week)

fun EventWithWeek.toEventModel():EventModel = EventModel(date = this.event.date.toLocalDate(),
    taskId = this.event.task_id, time = this.event.time, week = this.week)



fun TaskCurrentTime.toTaskTimeModel(): TaskTimeModel = TaskTimeModel(
    task = this.task.toTaskModel(), currentTime = this.currentTime)

fun TaskTimeModel.toTaskCurrentTime(): TaskCurrentTime = TaskCurrentTime(
    task = this.task.toTask(), currentTime = this.currentTime)



fun TaskWithEvents.toTaskEventsModel(): TaskEventsModel = TaskEventsModel(
    task = this.task.toTaskModel(), events = this.events.map{it.toEventModel()})

fun TaskEventsModel.toTaskWithEvents(): TaskWithEvents = TaskWithEvents(
    task = this.task.toTask(), events = this.events.map{it.toEventWithWeek()})



