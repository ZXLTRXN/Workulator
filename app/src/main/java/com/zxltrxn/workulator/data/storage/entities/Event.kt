package com.zxltrxn.workulator.data.storage.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import java.time.LocalDate

@Entity(primaryKeys = ["date","task_id"],
    foreignKeys = arrayOf(ForeignKey(entity = Task::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("task_id"),
        onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Date::class,
        parentColumns = arrayOf("date"),
        childColumns = arrayOf("date"),
        onDelete = ForeignKey.NO_ACTION)))
data class Event(

    var date: Long,
    var task_id:Long,
    @NonNull
    var time:Int
)