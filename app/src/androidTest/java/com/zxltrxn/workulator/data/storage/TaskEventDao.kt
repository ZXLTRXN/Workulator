package com.zxltrxn.workulator.data.storage



import com.zxltrxn.workulator.data.storage.entities.Task
import com.zxltrxn.workulator.di.dataModule
import com.zxltrxn.workulator.utils.isEquivalent

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class TaskEventDaoTest: KoinTest {
    val dao by inject<TaskEventDao>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(dataModule)
    }

    @Test
    fun shouldAddTask(){
        val tasks = listOf(Task(name = "work", target_time = 40,presets = listOf(50,90),is_active = true),
            Task(name = "sleep", target_time = 56,presets = listOf(480,90),is_active = false))
        for( task in tasks)
            dao.insertTask(task)
        val ids = dao.getTaskIds().value
        assertEquals(ids.size, tasks.size)
        for (i in tasks.indices){
            val taskWithTime = dao.readTaskWithTime(ids[i],5)
            assertTrue(taskWithTime.currentTime == 0 && taskWithTime.task.isEquivalent(tasks[i]))
        }
    }
}