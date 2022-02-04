package com.zxltrxn.workulator.data.storage.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zxltrxn.workulator.utils.Constants.DEFAULT_ID

@Entity
data class Task (
    @NonNull
    var name:String,
    @NonNull
    var target_time:Int,
    @NonNull
    var presets:List<Int>,
    @NonNull
    var active:Boolean,

    @PrimaryKey(autoGenerate = true)
    var task_id:Long = DEFAULT_ID,
)