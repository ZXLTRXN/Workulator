package com.zxltrxn.workulator.data.storage

import androidx.room.Dao
import com.zxltrxn.workulator.data.models.EventModelStorage
import com.zxltrxn.workulator.data.models.TaskEventsModelStorage
import com.zxltrxn.workulator.data.models.TaskModelStorage
import com.zxltrxn.workulator.data.models.TaskTimeModelStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface TaskEventDao {

    fun insertTask(task: TaskModelStorage): Boolean
    fun getTaskIds(): StateFlow<List<UInt>>
    fun updateTask(task: TaskModelStorage):Boolean

    fun readTaskWithTime(id: UInt): TaskTimeModelStorage
    fun getAllTasksWithEvents(): Flow<List<TaskEventsModelStorage>>

    fun insertEvent(event: EventModelStorage): Boolean
}