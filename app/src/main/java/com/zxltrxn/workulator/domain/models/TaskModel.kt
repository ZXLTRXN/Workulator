package com.zxltrxn.workulator.domain.models

data class TaskModel(val id: UInt,
                val name:String,
                val targetTime:Int,
                val presets:List<Int>,
                val isActive:Boolean = true)