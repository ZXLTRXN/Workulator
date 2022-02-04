package com.zxltrxn.workulator.data.storage.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Date(
    @PrimaryKey
    var date:Long,
    @NonNull
    var week_num:Int
)