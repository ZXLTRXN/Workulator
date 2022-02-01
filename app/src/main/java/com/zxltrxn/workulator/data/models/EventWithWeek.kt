package com.zxltrxn.workulator.data.models

import com.zxltrxn.workulator.data.storage.entities.Event
import java.time.LocalDate


data class EventWithWeek(val event: Event, val week:Int)