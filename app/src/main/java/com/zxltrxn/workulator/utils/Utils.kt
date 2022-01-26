package com.zxltrxn.workulator.utils

import android.content.Context
import android.widget.Toast
import com.zxltrxn.workulator.data.storage.models.TaskModelStorage
import com.zxltrxn.workulator.domain.models.Period
import com.zxltrxn.workulator.domain.models.TaskModel
import java.util.*

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun TaskModel.toTaskModelStorage():TaskModelStorage = TaskModelStorage(id = this.id,name = this.name,
        targetTime = this.targetTime,period = this.period.minutes,isActive = this.isActive)

fun TaskModelStorage.toTaskModel():TaskModel {
    val period:Period = when(this.period){
        1440->Period.DAY
        10080->Period.WEEK
        else-> throw InputMismatchException("TaskModelStorage to TaskModel period not in Enum")
    }
    return TaskModel(id = this.id,name = this.name,
        targetTime = this.targetTime,period = period,isActive = this.isActive)
}

