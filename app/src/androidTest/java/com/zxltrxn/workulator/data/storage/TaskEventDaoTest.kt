package com.zxltrxn.workulator.data.storage

import android.util.Log
import org.junit.Test
import org.junit.runner.RunWith

import org.koin.test.KoinTest

import org.koin.test.inject
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.zxltrxn.workulator.data.models.TaskCurrentTime
import com.zxltrxn.workulator.data.storage.entities.Date
import com.zxltrxn.workulator.data.storage.entities.Event
import com.zxltrxn.workulator.data.storage.entities.Task
import com.zxltrxn.workulator.di.roomTestModule
import com.zxltrxn.workulator.utils.*

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

import org.koin.core.context.GlobalContext.loadKoinModules

import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.context.GlobalContext.startKoin
import org.koin.test.KoinTestRule
import java.time.LocalDate
import java.util.*


@RunWith(AndroidJUnit4::class)
class TaskEventDaoTest: KoinTest {
    private val dao by inject<TaskEventDao>()

    private val size = listOf(3,7)
    private val time =  listOf(100,10)
    private val date = listOf(
        LocalDate.of(2021, 1, 30),//1
        LocalDate.of(2021, 1, 31),//2
        LocalDate.of(2021, 2, 6)//2
    )


    @Before
    fun before() {
//        loadKoinModules(roomTestModule)
        if (GlobalContext.getOrNull() == null) {
            startKoin {
               androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
                modules(roomTestModule) }
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun shouldAddTask() {
        val tasks = TestUtils.randomTasks(3)
        for (task in tasks)
            dao.insertTask(task)

        val ids = dao.getTaskIds()
        assertEquals(ids.size, tasks.size)

        for (i in ids.indices) {
            val task = dao.getTaskById(ids[i])
            assertTrue(task.isEquivalent(tasks[i]))
        }
    }

    @Test
    fun shouldUpdateTask() {
        val task = TestUtils.randomTasks(1).get(0)
        dao.insertTask(task)

        val id = dao.getTaskIds().get(0)
        val newTask = dao.getTaskById(id)
        newTask.presets = listOf(99, 0)
        newTask.target_time = 60
        newTask.active = false
        dao.updateTask(newTask)
        val resultTask = dao.getTaskById(id)
        assertEquals(newTask, resultTask)
    }

    @Test
    fun shouldReturnTaskIdsCorrectly() {
        var ids = dao.getTaskIds()
        assertEquals(ids.size, 0)
        assertEquals(ids, listOf<Long>())

        val task = TestUtils.randomTasks(1).get(0)
        dao.insertTask(task)

        ids = dao.getTaskIds()
        assertEquals(ids.size, 1)
        assertEquals(ids, listOf<Long>(1))
    }

    private fun insertRandomTask(task: Task? = null): Long {
        val addedTask = task ?: TestUtils.randomTasks(1).get(0)
        dao.insertTask(addedTask)
        return dao.getTaskIds().get(0)
    }

    @Test
    fun shouldInsertEventsCorrectly() {
        val id = insertRandomTask()
        val i = 0

        val events = TestUtils.randomEventModel(
            date = date[i],
            taskId = id,
            size = size[i],
            time = time[i]
        ).plus(
            TestUtils.randomEventModel(
                date = date[i + 1],
                taskId = id,
                size = size[i + 1],
                time = time[i + 1]
            )
        )

        val expEvents = listOf(
            Event(date = date[i].toLong(), task_id = id, time = size[i] * time[i]),
            Event(date = date[i + 1].toLong(), task_id = id, time = size[i + 1] * time[i + 1])
        )

        for (event in events)
            dao.insertEventWithWeek(date = event.toDate(), event = event.toEvent())

        val resEvents = dao.getEventById(id)
        assertEquals(expEvents, resEvents)
    }

    @Test
    fun shouldInsertDateWithWeekWhileInsertEventCorrectly() {
        val id = insertRandomTask()
        val i = 0

        val events = TestUtils.randomEventModel(
            date = date[i],
            taskId = id,
            size = size[i],
            time = time[i]
        ).plus(TestUtils.randomEventModel(
            date = date[i + 1],
            taskId = id,
            size = size[i + 1],
            time = time[i + 1])
        ).plus(TestUtils.randomEventModel(
            date = date[i + 2],
            taskId = id,
            size = size[i],
            time = time[i])
        )

        for (event in events)
            dao.insertEventWithWeek(date = event.toDate(), event = event.toEvent())

        val resDate = dao.getAllDate()

        val expDate = listOf(
            Date(date = date[i].toLong(), week_num = resDate[0].week_num),
            Date(date = date[i + 1].toLong(), week_num = resDate[0].week_num + 1),
            Date(date = date[i + 2].toLong(), week_num = resDate[0].week_num + 1)
        )
        assertEquals(expDate, resDate)
    }

    @Test
    fun shouldReturnTasksWithTimeCorrectly(){
        val task = Task(name = "a", target_time = 500, presets = listOf(100,200),active = true)
        val id = insertRandomTask(task)
        task.id = id
//        val id1 = insertRandomTask()
        val i = 0

        val events = TestUtils.randomEventModel(
            date = date[i],
            taskId = id,
            size = size[i],
            time = time[i]
        ).plus(TestUtils.randomEventModel(
            date = date[i + 1],
            taskId = id,
            size = size[i],
            time = time[i])
        ).plus(TestUtils.randomEventModel(
            date = date[i + 2],
            taskId = id,
            size = size[i + 1],
            time = time[i + 1])
        )


        for (event in events)
            dao.insertEventWithWeek(date = event.toDate(), event = event.toEvent())
        val week = dao.getAllDate().get(1).week_num
        val  resTask1 = dao.getTasksWithTime(week)
        val  resTask2 = dao.getTasksWithTime(week-1)
        val  resTask3 = dao.getTasksWithTime(week+5)

        var expTime1 = 0
        for( j in size.indices)
            expTime1 += size[j]*time[j]

        val expTime2 = size[i] * time[i]

        val  expTask1 = listOf(TaskCurrentTime(task = task, currentTime = expTime1)) // несколько event
        val  expTask2 = listOf(TaskCurrentTime(task = task, currentTime = expTime2)) // один event
        val  expTask3 = listOf(TaskCurrentTime(task = task, currentTime = 0)) //нет event
        assertEquals(expTask1,resTask1)
        assertEquals(expTask2,resTask2)
        assertEquals(expTask3,resTask3)
    }
}




