package com.zxltrxn.workulator.data.storage

import com.zxltrxn.workulator.data.storage.models.TaskModelStorage

interface TaskStorage {
    fun save(task: TaskModelStorage):Boolean
    fun getAll(): List<TaskModelStorage>
}