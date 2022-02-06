package com.zxltrxn.workulator.utils

import com.zxltrxn.workulator.data.storage.entities.Task
import com.zxltrxn.workulator.domain.models.EventModel
import java.time.LocalDate


class TestUtils() {
    companion object {
        fun randomInt(until: Int, from: Int = 0, ): Int =
            kotlin.random.Random.nextInt(from, until)

        fun randomString(length: Int): String {
            val charPool: List<Char> =
                ('a'..'z') + ('A'..'Z') + ('а'..'я') + ('А'..'Я') + ('0'..'9')
            return (1..length)
                .map { i -> randomInt(charPool.size) }
                .map(charPool::get)
                .joinToString("")
        }

        fun randomTasks(size: Int): List<Task> {
            val length = 20
            val maxVal = 168

            val list = mutableListOf<Task>()
            for (i in 0 until size) {
                list.add(
                    Task(
                        name = randomString(length),
                        target_time = randomInt(maxVal, 1),
                        presets = listOf(randomInt(maxVal), randomInt(maxVal)),
                        active = true
                    )
                )
            }
            return list
        }

        fun randomEventModel(size:Int, date:LocalDate = LocalDate.now(),taskId:Long = 1,
                             time:Int = randomInt(500)):List<EventModel> {
            val list = mutableListOf<EventModel>()
            val week = date.getWeek()
            for (i in 0 until size) {
                list.add(EventModel(
                    date = date,
                    taskId = taskId,
                    time = time,
                    week = week))
            }
            return list
        }
    }
}

