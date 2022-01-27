package com.zxltrxn.workulator.data.storage.db

import android.content.Context
import com.zxltrxn.workulator.data.storage.TaskEventStorage
import com.zxltrxn.workulator.data.storage.models.EventModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskEventsModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskModelStorage
import com.zxltrxn.workulator.data.storage.models.TaskTimeModelStorage
import com.zxltrxn.workulator.domain.models.TaskEventsModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import kotlinx.coroutines.flow.Flow

class DBTaskStorage(private val context: Context): TaskEventStorage {
    override fun insertTask(task: TaskModelStorage): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateTask(task: TaskModelStorage): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTasksWithEvents(): Flow<List<TaskEventsModelStorage>> {
        TODO("Not yet implemented")
    }

    override fun insertEvent(event: EventModelStorage): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTaskIds(): Flow<List<UInt>> {
        TODO("Not yet implemented")
    }

    override fun readTaskWithTime(id: UInt): TaskTimeModelStorage {
        TODO("Not yet implemented")
    }

}