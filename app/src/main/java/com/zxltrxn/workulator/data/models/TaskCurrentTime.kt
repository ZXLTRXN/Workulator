package com.zxltrxn.workulator.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.zxltrxn.workulator.data.storage.entities.Task

//data class TaskCurrentTime(var task: Task, var currentTime:Int)


data class TaskCurrentTime(
    @Embedded
    var task: Task,
    var currentTime:Int)