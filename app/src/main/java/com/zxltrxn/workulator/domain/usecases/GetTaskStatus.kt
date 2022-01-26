package com.zxltrxn.workulator.domain.usecases

import com.zxltrxn.workulator.domain.TaskRepository
import com.zxltrxn.workulator.domain.models.TaskModel

class GetTaskStatus(private val taskRepo: TaskRepository) {
    init{
        readTasks()
    }

    private lateinit var tasks:Array<TaskModel>
    private var it = -1

    fun next():TaskModel?{
        if(tasks.isEmpty()) return null
        if(it != tasks.size - 1) it++
        return tasks[it]
    }

    fun previous():TaskModel?{
        if(tasks.isEmpty()) return null
        if(it > 0) it--
        return tasks[it]
    }

    private fun readTasks(){
        tasks = taskRepo.readTasks().toTypedArray()
    }
}