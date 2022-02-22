package com.zxltrxn.workulator.presentation

import com.zxltrxn.workulator.domain.models.TaskTimeModel
import com.zxltrxn.workulator.utils.Constants.DEFAULT_TASK_INDEX


//How to Make Your Code Clean With Kotlin Sealed Classes
data class UIState(val isLoading:Boolean = false,
                   val error:String? = null, //error class better
                   val items: List<TaskTimeModel> = listOf<TaskTimeModel>(),
                   val index:Int = DEFAULT_TASK_INDEX // means need to add Task
)