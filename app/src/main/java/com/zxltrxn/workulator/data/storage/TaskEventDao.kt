package com.zxltrxn.workulator.data.storage

import androidx.room.*
import com.zxltrxn.workulator.data.models.EventWithWeek
import com.zxltrxn.workulator.data.models.TaskWithEvents
import com.zxltrxn.workulator.data.models.TaskCurrentTime
import com.zxltrxn.workulator.data.storage.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface TaskEventDao {

    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("select id from task")
    fun getTaskIds(): StateFlow<List<UInt>>


    @Query("select task.*, culc_time.* " +
            "from task join (" +
                "select sum(event.time) " +
                "from event join date on event.date = date.date " +
                "where event.task_id=:id and date.week_num =:week) as culc_time")
    fun readTaskWithTime(id: UInt, week:Int): TaskCurrentTime

    @Query("select task.* " +
            "from task join (" +
                "select event.*, date.week_num " +
                "from event join date on date.date = event.date) as event_week " +
            "on event_week.task_id = task.id")
    fun getAllTasksWithEvents(): Flow<List<TaskWithEvents>>

//    @Transaction
//    @Query("")
//    https://developer.android.com/reference/android/arch/persistence/room/Transaction
    @Insert
    fun insertEvent(event: EventWithWeek)
}