package com.zxltrxn.workulator.domain.models

import com.zxltrxn.workulator.utils.Constants.DEFAULT_ID

data class TaskModel(val name:String,
                val targetTime:Int,
                val presets:List<Int>,
                val isActive:Boolean = true,
                 val id: Long = DEFAULT_ID)