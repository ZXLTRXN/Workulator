package com.zxltrxn.workulator.utils

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.zxltrxn.workulator.data.models.*
import com.zxltrxn.workulator.data.storage.entities.*
import com.zxltrxn.workulator.data.storage.entities.Date
import com.zxltrxn.workulator.domain.models.*
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}



fun LocalDate.toLong():Long = this.toEpochDay()

fun LocalDate.getWeek():Int = this.get(WeekFields.of(Locale.US).weekOfYear())

fun Long.toLocalDate():LocalDate = LocalDate.ofEpochDay(this)



fun TaskModel.toTask(): Task = Task(id = this.id,name = this.name,
        target_time = this.targetTime,presets = this.presets, active = this.isActive)

fun Task.toTaskModel():TaskModel = TaskModel(id = this.id, name = this.name,
        targetTime = this.target_time, presets = this.presets, isActive = this.active)



fun EventModel.toEvent():Event = Event(date = this.date.toLong(), task_id = this.taskId,time = this.time)

fun EventModel.toDate():Date = Date(date = this.date.toLong(), week_num = this.week)

fun EventModel.toEventWithWeek():EventWithWeek = EventWithWeek(this.toEvent(), week = this.week)

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


fun Task.isEquivalent(other:Task): Boolean = (this.name == other.name &&
        this.target_time == other.target_time && this.presets == other.presets &&
        this.active == other.active)



fun Int.toHoursMinutes():Array<Int>{
    val hours = this.div(60)
    val minutes = this.rem(60)
    return arrayOf(hours,minutes)
}

fun Int.toTimeString():String {
    val t = this.toHoursMinutes()
    return if(this == 0 ||t[0] == 0) "${t[1]} мин"
    else
        if(t[1] == 0) "${t[0]} ч"
        else "${t[0]} ч\n${t[1]} мин"


}





