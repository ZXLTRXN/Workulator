package com.zxltrxn.workulator.domain.models

data class TaskModel(val id: UInt,
                val name:String,
                val targetTime:Int,
                val period:Period,
                val isActive:Boolean = true)