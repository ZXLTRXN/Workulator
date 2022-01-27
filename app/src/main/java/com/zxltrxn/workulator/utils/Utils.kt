package com.zxltrxn.workulator.utils

import android.content.Context
import android.widget.Toast
import com.zxltrxn.workulator.data.storage.models.EventModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskEventsModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskTimeModelStorage
import com.zxltrxn.workulator.domain.models.*
import java.util.*

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun TaskModel.toTaskModelStorage():TaskModelStorage = TaskModelStorage(id = this.id,name = this.name,
        targetTime = this.targetTime,period = this.period.minutes,isActive = this.isActive)


fun TaskModelStorage.toTaskModel():TaskModel {
    val period:Period = when(this.period){
        Period.DAY.minutes->Period.DAY
        Period.WEEK.minutes->Period.WEEK
        else-> throw InputMismatchException("TaskModelStorage to TaskModel period not in Enum")
    }
    return TaskModel(id = this.id,name = this.name,
        targetTime = this.targetTime,period = period,isActive = this.isActive)
}


fun EventModel.toEventModelStorage():EventModelStorage = EventModelStorage(date = this.date,
    taskId = this.taskId, time = this.time, week = this.week)

fun EventModelStorage.toEventModel():EventModel = EventModel(date = this.date,
    taskId = this.taskId, time = this.time, week = this.week)


fun TaskTimeModelStorage.toTaskTimeModel(): TaskTimeModel = TaskTimeModel(
    task = this.task.toTaskModel(), currentTime = this.currentTime)

fun TaskTimeModel.toTaskTimeModelStorage(): TaskTimeModelStorage = TaskTimeModelStorage(
    task = this.task.toTaskModelStorage(), currentTime = this.currentTime)


fun TaskEventsModelStorage.toTaskEventsModel(): TaskEventsModel = TaskEventsModel(
    task = this.task.toTaskModel(), events = this.events.map{it.toEventModel()}
)

fun TaskEventsModel.toTaskEventsModelStorage(): TaskEventsModelStorage = TaskEventsModelStorage(
    task = this.task.toTaskModelStorage(), events = this.events.map{it.toEventModelStorage()}
)