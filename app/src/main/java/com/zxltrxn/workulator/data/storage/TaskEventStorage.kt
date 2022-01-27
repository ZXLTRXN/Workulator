package com.zxltrxn.workulator.data.storage

import com.zxltrxn.workulator.data.storage.models.EventModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskEventsModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskTimeModelStorage
import com.zxltrxn.workulator.domain.models.TaskEventsModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import kotlinx.coroutines.flow.Flow

interface TaskEventStorage {
    fun insertTask(task: TaskModelStorage): Boolean
    fun updateTask(task: TaskModelStorage):Boolean
    fun getAllTasksWithEvents(): Flow<List<TaskEventsModelStorage>>

    fun insertEvent(event: EventModelStorage): Boolean

    fun getTaskIds():Flow<List<UInt>>

    fun readTaskWithTime(id: UInt): TaskTimeModelStorage
}