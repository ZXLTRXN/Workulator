package com.zxltrxn.workulator.data.repositories

import com.zxltrxn.workulator.data.storage.TaskEventDao
import com.zxltrxn.workulator.domain.models.TaskEventsModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import com.zxltrxn.workulator.domain.repositoryinterfaces.TaskEventsRepository
import com.zxltrxn.workulator.utils.toTaskEventsModel
import com.zxltrxn.workulator.utils.toTaskTimeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskEventsRepositoryImpl(private val storage: TaskEventDao):TaskEventsRepository {

    override fun readAllTasksWithEvents(): Flow<List<TaskEventsModel>> =
        storage.getAllTasksWithEvents().map{tasks->tasks.map{it.toTaskEventsModel()}}

    override fun readTaskWithTime(id: UInt, week:Int): TaskTimeModel =
        storage.readTaskWithTime(id, week).toTaskTimeModel()

}