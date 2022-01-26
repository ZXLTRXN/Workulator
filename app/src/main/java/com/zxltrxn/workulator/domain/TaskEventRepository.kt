package com.zxltrxn.workulator.domain

import com.zxltrxn.workulator.domain.models.TaskEventModel

interface TaskEventRepository {
    fun create(task: TaskEventModel):Boolean
    fun readTime(id: UInt): Int
}