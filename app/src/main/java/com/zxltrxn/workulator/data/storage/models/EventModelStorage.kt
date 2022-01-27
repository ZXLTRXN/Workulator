package com.zxltrxn.workulator.data.storage.models

import java.time.LocalDate


data class EventModelStorage(val date: LocalDate, val taskId:UInt, val time:Int, val week:Int)