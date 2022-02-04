package com.zxltrxn.workulator.data.models

import com.zxltrxn.workulator.data.storage.entities.Task

data class TaskWithEvents(var task: Task, var events:List<EventWithWeek>)