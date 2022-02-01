package com.zxltrxn.workulator.data.storage.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true) val id:UInt,
    @NonNull val name:String,
    @NonNull val target_time:Int,
    @NonNull val presets:List<Int>,
    @NonNull val is_active:Boolean
)