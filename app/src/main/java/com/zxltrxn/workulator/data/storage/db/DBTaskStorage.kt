package com.zxltrxn.workulator.data.storage.db

import android.content.Context
import com.zxltrxn.workulator.data.storage.TaskStorage
import com.zxltrxn.workulator.data.storage.models.TaskModelStorage

class DBTaskStorage(private val context: Context): TaskStorage {
    override fun save(task: TaskModelStorage): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<TaskModelStorage> {
        TODO("Not yet implemented")
    }
}