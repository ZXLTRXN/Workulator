package com.zxltrxn.workulator.data.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Date(
    @PrimaryKey val date:Long,
    val week_num:Int
)