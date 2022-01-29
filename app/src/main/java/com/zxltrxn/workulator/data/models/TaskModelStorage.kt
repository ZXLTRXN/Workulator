package com.zxltrxn.workulator.data.models

data class TaskModelStorage(val id: UInt,
                            val name:String,
                            val targetTime:Int,
                            val period:Int,
                            val presets:List<Int>,
                            val isActive:Boolean)