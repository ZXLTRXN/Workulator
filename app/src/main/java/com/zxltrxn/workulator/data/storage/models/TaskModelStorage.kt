package com.zxltrxn.workulator.data.storage.models

data class TaskModelStorage(val id: UInt,
                            val name:String,
                            val targetTime:Int,
                            val period:Int,
                            val isActive:Boolean) {
}