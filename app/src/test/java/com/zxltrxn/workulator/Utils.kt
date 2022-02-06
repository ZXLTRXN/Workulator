package com.zxltrxn.workulator

import com.zxltrxn.workulator.data.models.*
import com.zxltrxn.workulator.data.storage.entities.*
import com.zxltrxn.workulator.domain.models.*
import com.zxltrxn.workulator.utils.*
import com.zxltrxn.workulator.utils.Constants.DEFAULT_WEEK
import org.junit.Assert.*

import org.junit.Test
import java.time.LocalDate


class Utils {

    val id = arrayOf(10,3523513222)
    val size = id.indices
    val name = arrayOf(",к112","Работать в поте лица")
    val target = arrayOf(2500,-1)
    val isActive = arrayOf(false,true)
    val presets = listOf(200,3256)
    val date = arrayOf(LocalDate.now(),LocalDate.of(2018, 12, 1))
    val week = arrayOf(DEFAULT_WEEK,7)

    @Test
    fun shouldConvertLocalTimeToLongCorrectly(){
        for (i in size){
            val a = date[i].toLong()
            val b = a.toLocalDate()
            assertEquals(date[i],b)
        }
    }

    @Test
    fun shouldConvertTaskModelToTaskCorrectly(){
        for(i in size){
            val expected =  TaskModel(id = id[i],name = name[i],
                targetTime = target[i], presets = presets, isActive = isActive[i])

            val actual = Task(id = id[i],name = name[i],
                target_time = target[i], presets = presets, active = isActive[i])

            assertEquals(actual.toTaskModel(),expected)
            assertEquals(expected.toTask(),actual)
        }
    }

    @Test
    fun shouldConvertEventModelToEventWithWeekCorrectly(){
        for(i in size){
            val expected =  EventModel(date = date[i], taskId = id[i],
                time = target[i],week = week[i])

            val event = Event(date = date[i].toLong(), task_id = id[i],time = target[i])

            val actual =  EventWithWeek(event, week = week[i])

            assertEquals(actual.toEventModel(),expected)
            assertEquals(expected.toEventWithWeek(),actual)
        }
    }

    @Test
    fun shouldConvertEventModelToEventCorrectly(){
        for(i in size){
            val expected =  EventModel(date = date[i], taskId = id[i],
                time = target[i],week = week[i])

            val actual = Event(date = date[i].toLong(), task_id = id[i],time = target[i])

            assertEquals(expected.toEvent(),actual)
        }
    }

    @Test
    fun shouldConvertEventModelToDateCorrectly(){
        for(i in size){
            val expected =  EventModel(date = date[i], taskId = id[i],
                time = target[i],week = week[i])

            val actual = Date(date = date[i].toLong(), week_num = week[i])

            assertEquals(expected.toDate(),actual)
        }
    }

    @Test
    fun shouldConvertTaskTimeModelToTaskCurrentTimeCorrectly(){
        for(i in size){
            val task = TaskModel(id = id[i],name = name[i],
                targetTime = target[i], presets = presets, isActive = isActive[i])

            val expected =  TaskTimeModel(task = task, currentTime = target[i])

            val actual =  TaskCurrentTime(task = task.toTask(), currentTime = target[i])

            assertEquals(actual.toTaskTimeModel(),expected)
            assertEquals(expected.toTaskCurrentTime(),actual)
        }
    }

    @Test
    fun shouldConvertTaskEventsModelToTaskWithEventsCorrectly(){
        for(i in size){
            val task = TaskModel(id = id[i],name = name[i],
                targetTime = target[i], presets = presets, isActive = isActive[i])

            val task1 = Task(id = id[i],name = name[i],
                target_time = target[i], presets = presets, active = isActive[i])

            val events = mutableListOf<EventModel>()
            val events1 = mutableListOf<EventWithWeek>()
            for (j in size){
                events.add(EventModel(date = date[j], taskId = id[j],
                    time = target[j],week = week[j]))

                val e = Event(date = date[j].toLong(), task_id = id[j],time = target[j])
                events1.add(EventWithWeek(e, week = week[j]))
            }

            val expected =  TaskEventsModel(task = task, events = events)

            val actual =  TaskWithEvents(task = task1, events = events1)

            assertEquals(actual.toTaskEventsModel(),expected)
            assertEquals(expected.toTaskWithEvents(),actual)
        }
    }

    @Test
    fun shouldCheckEquivalentCorrectly(){
        var i = 0
        val t1 = Task(id = id[i],name = name[i],
            target_time = target[i], presets = presets, active = isActive[i])

        val t2 = Task(id = id[i+1],name = name[i],
            target_time = target[i], presets = presets, active = isActive[i])

        val t3 = Task(id = id[i],name = name[i+1],
            target_time = target[i], presets = presets, active = isActive[i])

        assertTrue(t1.isEquivalent(t2))
        assertFalse(t1.isEquivalent(t3))



    }
}
