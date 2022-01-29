package com.zxltrxn.workulator

import com.zxltrxn.workulator.data.models.EventModelStorage
import com.zxltrxn.workulator.data.models.TaskEventsModelStorage
import com.zxltrxn.workulator.data.models.TaskModelStorage
import com.zxltrxn.workulator.data.models.TaskTimeModelStorage
import com.zxltrxn.workulator.domain.models.*
import com.zxltrxn.workulator.utils.*
import com.zxltrxn.workulator.utils.Constants.DEFAULT_WEEK
import org.junit.Assert.assertEquals

import org.junit.Test
import java.time.LocalDate


class Utils {

    val id = arrayOf(10u,3523513222u)
    val size = id.indices
    val name = arrayOf(",к112","Работать в поте лица")
    val target = arrayOf(2500,-1)
    val period = arrayOf(Period.WEEK,Period.DAY)
    val isActive = arrayOf(false,true)
    val presets = listOf(200,3256)

    val date = arrayOf(LocalDate.now(),LocalDate.of(2018, 12, 1))
    val week = arrayOf(DEFAULT_WEEK,7)


    @Test
    fun shouldConvertTaskModelToTaskModelStorageCorrectly(){
        for(i in size){
            val expected =  TaskModel(id = id[i],name = name[i],
                targetTime = target[i], period = period[i], presets = presets, isActive = isActive[i])

            val actual = TaskModelStorage(id = id[i],name = name[i],
                targetTime = target[i], period = period[i].minutes, presets = presets, isActive = isActive[i])

            assertEquals(actual.toTaskModel(),expected)
            assertEquals(expected.toTaskModelStorage(),actual)
        }
    }

    @Test
    fun shouldConvertEventModelToEventModelStorageCorrectly(){
        for(i in size){
            val expected =  EventModel(date = date[i], taskId = id[i],
                time = target[i],week = week[i])

            val actual =  EventModelStorage(date = date[i], taskId = id[i],
                time = target[i],week = week[i])

            assertEquals(actual.toEventModel(),expected)
            assertEquals(expected.toEventModelStorage(),actual)
        }
    }

    @Test
    fun shouldConvertTaskTimeModelToTaskTimeModelStorageCorrectly(){
        for(i in size){
            val task = TaskModel(id = id[i],name = name[i],
                targetTime = target[i], period = period[i],
                presets = presets, isActive = isActive[i])

            val expected =  TaskTimeModel(task = task, currentTime = target[i])

            val actual =  TaskTimeModelStorage(task = task.toTaskModelStorage(), currentTime = target[i])

            assertEquals(actual.toTaskTimeModel(),expected)
            assertEquals(expected.toTaskTimeModelStorage(),actual)
        }
    }

    @Test
    fun shouldConvertTaskEventsModelToTaskEventsModelStorageCorrectly(){
        for(i in size){
            val task = TaskModel(id = id[i],name = name[i],
                targetTime = target[i], period = period[i],
                presets = presets, isActive = isActive[i])

            val task1 = TaskModelStorage(id = id[i],name = name[i],
                targetTime = target[i], period = period[i].minutes,
                presets = presets, isActive = isActive[i])

            val events =  listOf(
                EventModel(date = date[0], taskId = id[0],
                time = target[0],week = week[0]),
                EventModel(date = date[1], taskId = id[1],
                time = target[1],week = week[1]))

            val events1 =  listOf(
                EventModelStorage(date = date[0], taskId = id[0],
                    time = target[0],week = week[0]),
                EventModelStorage(date = date[1], taskId = id[1],
                    time = target[1],week = week[1]))

            val expected =  TaskEventsModel(task = task, events = events)

            val actual =  TaskEventsModelStorage(task = task1, events = events1)

            assertEquals(actual.toTaskEventsModel(),expected)
            assertEquals(expected.toTaskEventsModelStorage(),actual)
        }
    }
}
