package com.zxltrxn.workulator.domain.models

import com.zxltrxn.workulator.utils.Constants.DEFAULT_WEEK
import java.time.LocalDate

data class EventModel(val date: LocalDate, val taskId:UInt, val time:Int, var week:Int=DEFAULT_WEEK)