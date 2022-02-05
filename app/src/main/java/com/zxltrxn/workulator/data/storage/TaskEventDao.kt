package com.zxltrxn.workulator.data.storage

import androidx.room.*
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

    @Query("select task_id from task")
    fun getTaskIds(): Flow<List<Long>>

    @Query("select task.*, tmp.currentTime " +
            "from task join (" +
                "select sum(event.time) as currentTime " +
                "from event join date on event.date = date.date " +
                "where event.task_id=:id and date.week_num =:week) as tmp")
    fun readTaskWithTime(id: Long, week:Int): TaskCurrentTime

//    @Query("select task.* " +
//            "from task join (" +
//                "select event.*, date.week_num " +
//                "from event join date on date.date = event.date) as event_week " +
//            "on event_week.task_id = task.task_id")
//    fun getAllTasksWithEvents(): Flow<List<TaskWithEvents>>

//    https://developer.android.com/reference/android/arch/persistence/room/Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDate(date: Date)

    @Insert
    fun insertEvent(event: Event)

    @Transaction
    fun insertEventWithWeek(date: Date, event: Event){
        insertDate(date)
        insertEvent(event)
    }

}