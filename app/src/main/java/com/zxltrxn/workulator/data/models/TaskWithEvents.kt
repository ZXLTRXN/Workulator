package com.zxltrxn.workulator.data.models

import com.zxltrxn.workulator.data.storage.entities.Task

data class TaskWithEvents(val task: Task, val events:List<EventWithWeek>)