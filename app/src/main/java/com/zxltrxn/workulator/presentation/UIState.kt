package com.zxltrxn.workulator.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

//How to Make Your Code Clean With Kotlin Sealed Classes
data class UIState(val isLoading:Boolean = false,
                   val error:String? = null, //error class better
                   val items: List<TaskTimeModel> = listOf<TaskTimeModel>()
)