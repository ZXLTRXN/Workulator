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

    @Query("select id from task")
    fun getTaskIds(): List<Long>

    @Query("select task.*, tmp.currentTime " +
            "from task join (" +
                "select sum(event.time) as currentTime " +
                "from event join date on event.date = date.date " +
                "where event.task_id=:id and date.week_num =:week) as tmp")
    fun getTaskWithTime(id: Long, week:Int): TaskCurrentTime

//    @Query("select task.* " +
//            "from task join (" +
//                "select event.*, date.week_num " +
//                "from event join date on date.date = event.date) as event_week " +
//            "on event_week.task_id = task.task_id")
//    fun getAllTasksWithEvents(): Flow<List<TaskWithEvents>>

//    https://developer.android.com/reference/android/arch/persistence/room/Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDate(date: Date)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvent(event: Event):Long

    @Query("update event set time = time+:time where task_id = :task_id and date = :date")
    fun updateEvent(date:Long, task_id: Long, time:Int)

//https://stackoverflow.com/questions/45677230/android-room-persistence-library-upsert
    @Transaction
    fun insertEventWithWeek(date: Date, event: Event){
        insertDate(date)
        val st:Long = insertEvent(event)
        if( st == -1L){
            updateEvent(date = event.date, task_id = event.task_id, time = event.time)
        }
    }

    //Test
    @Query("select event.* from event " +
            "where event.task_id=:id")
    fun getEventById(id:Long):List<Event>

    @Query("select date.* from date ")
    fun getAllDate():List<Date>

    @Query("select task.* from task " +
            "where task.id=:id")
    fun getTaskById(id:Long):Task

}