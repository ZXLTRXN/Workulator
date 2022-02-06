package com.zxltrxn.workulator.data.storage

import android.util.Log
import org.junit.Test
import org.junit.runner.RunWith

import org.koin.test.KoinTest

import org.koin.test.inject
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zxltrxn.workulator.data.storage.entities.Date
import com.zxltrxn.workulator.data.storage.entities.Event
import com.zxltrxn.workulator.di.roomTestModule
import com.zxltrxn.workulator.utils.*

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.koin.core.context.GlobalContext.loadKoinModules

import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.context.GlobalContext.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.core.context.startKoin
import org.koin.test.KoinTestRule
import java.time.LocalDate
import java.util.*


@RunWith(AndroidJUnit4::class)
class TaskEventDaoTest: KoinTest {
    private val dao by inject<TaskEventDao>()

    private val size = listOf(3,7)
    private val time =  listOf(100,10)
    private val date = listOf(
        LocalDate.of(2021, 1, 30),
        LocalDate.of(2021, 1, 31),
        LocalDate.of(2021, 2, 6)
    )


//    @get:Rule
//    val koinTestRule = KoinTestRule.create {
//        // Your KoinApplication instance here
//        modules(roomTestModule)
//    }

    @Before
    fun before() {
        loadKoinModules(roomTestModule)

//        startKoin {
//           androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
//            modules(roomTestModule) }
    }
//
//    @After
//    fun after() {
//        stopKoin()
//    }

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

    private fun insertRandomTask(): Long {
        val task = TestUtils.randomTasks(1).get(0)
        dao.insertTask(task)
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

//    @Test
//    fun shouldReturnTaskWithTimeCorrectly(){
//        val tasks = TestUtils.randomTasks(3)
//
//    }
}




