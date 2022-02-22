package com.zxltrxn.workulator.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.zxltrxn.workulator.R
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import com.zxltrxn.workulator.presentation.MainViewModel
import com.zxltrxn.workulator.ui.theme.elevation
import com.zxltrxn.workulator.ui.theme.spacing
import com.zxltrxn.workulator.utils.Constants.CUSTOM_PICKER_INDEX
import com.zxltrxn.workulator.utils.Constants.DEFAULT_PICKER_INDEX
import com.zxltrxn.workulator.utils.Constants.DEFAULT_TASK_INDEX
import com.zxltrxn.workulator.utils.Constants.TAG
import com.zxltrxn.workulator.utils.toTimeString


//@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProgressScreen(
    viewModel: MainViewModel,
    validateTask: (time: Int, name: String) -> Boolean,
    validateTime: (time: Int) -> Boolean
) {
    val configuration = LocalConfiguration.current

    var additionTaskHeight = 0.55f
    var pickTimeHeight = 0.4f
    val taskStatusHeight = 0.6f
    val allScreenHeight = 1f
    val eventBtnSize = 100.dp
    val pickTimeBtnSize = 40.dp
    val iteratorBtnSize = 60.dp


    var additionState by rememberSaveable { mutableStateOf(false) }
    var pickTimeState by rememberSaveable { mutableStateOf(false) }
    var pickerIndex by remember { mutableStateOf(DEFAULT_PICKER_INDEX) }


    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            additionTaskHeight = allScreenHeight
            pickTimeHeight = allScreenHeight
        }
        else -> {}
    }

//    AnimatedVisibility(visible = additionState) {
//        AdditionTaskCard(height = additionTaskHeight,
//            validation = validateTask, onSave = { time, name ->
//                viewModel.addTask(time = time, name = name)
//                additionState = false
//            },
//            onBack = { additionState = false })
//    }

    when {
        additionState -> AdditionTaskCard(height = additionTaskHeight,
            validation = validateTask, onSave = { time, name ->
                viewModel.addTask(time = time, name = name)
                additionState = false
            },
            onBack = { additionState = false })
        else -> {}
    }

    if (!viewModel.uiState.value.isLoading) {
        if (viewModel.uiState.value.index == DEFAULT_TASK_INDEX) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraLarge)
            )
            {
                EmptyTaskStatus(modifier = Modifier.fillMaxHeight(taskStatusHeight)) {
                    additionState = true
                }
                Spacer(modifier = Modifier.height(eventBtnSize))
                if(viewModel.uiState.value.items.isNotEmpty())
                    Iterators(modifier = Modifier, size = iteratorBtnSize,
                        isLeftActive = false,
                        onLeftClick = {},
                        onRightClick = {viewModel.nextTask()})
            }
        } else {
                val task = viewModel.uiState.value.items[viewModel.uiState.value.index]
                    if (pickTimeState) {
                        PickTimeCard(height = pickTimeHeight, validation = validateTime,
                            onSave = { time ->
                                pickTimeState = false
                                if (pickerIndex == CUSTOM_PICKER_INDEX) {
                                    viewModel.setEvent(id = task.task.id, time = time)
                                } else {
                                    viewModel.setPreset(
                                        task = task.task,
                                        newPreset = time, index = pickerIndex
                                    )
                                }
                                pickerIndex = DEFAULT_PICKER_INDEX
                            },
                            onBack = {
                                pickTimeState = false
                                pickerIndex = DEFAULT_PICKER_INDEX
                            })
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(MaterialTheme.spacing.medium)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraLarge),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            TaskStatus(
                                modifier = Modifier.fillMaxHeight(taskStatusHeight),
                                taskTime = task, onDelete = {viewModel.removeTask(task.task)}
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                PresetButton(time = task.task.presets[0],
                                    eventSize = eventBtnSize,
                                    pickTimeSize = pickTimeBtnSize,
                                    onPickTimeClick = {
                                        pickTimeState = true
                                        pickerIndex = 0
                                    },
                                    onSetEventClick = {
                                        viewModel.setEvent(
                                            id = task.task.id,
                                            time = task.task.presets[0]
                                        )
                                    })
                                SetEventButton(time = stringResource(id = R.string.ask_event_time),
                                    size = eventBtnSize,
                                    onClick = {
                                        pickTimeState = true
                                        pickerIndex = CUSTOM_PICKER_INDEX
                                    })
                                PresetButton(time = task.task.presets[1],
                                    eventSize = eventBtnSize,
                                    pickTimeSize = pickTimeBtnSize,
                                    onPickTimeClick = {
                                        pickTimeState = true
                                        pickerIndex = 1
                                    },
                                    onSetEventClick = {
                                        viewModel.setEvent(
                                            id = task.task.id,
                                            time = task.task.presets[1]
                                        )
                                    })
                            }
                            if(viewModel.uiState.value.items.size-1 == viewModel.uiState.value.index)
                                Iterators(modifier = Modifier, size = iteratorBtnSize,
                                    onLeftClick = {viewModel.previousTask()},
                                    isRightActive = false,
                                    onRightClick = {})
                            else
                                Iterators(modifier = Modifier, size = iteratorBtnSize,
                                onLeftClick = {viewModel.previousTask()},
                                onRightClick = {viewModel.nextTask()})
                        }
                    }
        }
    }
}

@Composable
fun TaskStatus(modifier: Modifier = Modifier, taskTime: TaskTimeModel, onDelete:()->Unit) {
    val percentage = taskTime.currentTime.toFloat() / taskTime.task.targetTime.toFloat()
    TaskStatusBox(modifier = modifier) {
//        Box(modifier = Modifier.padding(top = MaterialTheme.spacing.medium).background(Color.Red),
//            contentAlignment = Alignment.TopEnd){
//
//        }

        Box(modifier = Modifier.padding(MaterialTheme.spacing.medium),
            contentAlignment = Alignment.TopEnd){
            Icon(modifier = Modifier
                .then(Modifier.size(24.dp))
                .clickable{ onDelete() },
                painter = painterResource(id = R.drawable.ic_baseline_close_24),
                contentDescription = "delete Task")
        }

        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
        ) {

            Text(style = MaterialTheme.typography.h1, text = taskTime.task.name, textAlign = TextAlign.Center)

            CircularProgressBar(percentage = percentage, targetValue = taskTime.task.targetTime)
            Text(
                style = MaterialTheme.typography.h2, text = taskTime.currentTime.toTimeString(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun EmptyTaskStatus(modifier: Modifier = Modifier, onClick: () -> Unit) {
    TaskStatusBox(modifier = modifier) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.15f)
                    .clickable { onClick() },
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "add task"
            )
        }
    }
}

@Composable
fun TaskStatusBox(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(-1f)
            .padding(top = MaterialTheme.spacing.medium),
        color = MaterialTheme.colors.background,
        elevation = MaterialTheme.elevation.large,
        shape = MaterialTheme.shapes.small,
        content = content
    )
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    targetValue: Int,
    fontSize: TextUnit = 30.sp,
    radius: Dp = 90.dp,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 15.dp,
    animDuration: Int = 800,
    animDelay: Int = 0
) {

    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(1.dp.toPx(), cap = StrokeCap.Butt)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = targetValue.toTimeString(),
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

//            Text(text =(curPercentage.value * targetValue).toInt().toTimeString(),
//                fontSize = fontSize,
//                fontWeight = FontWeight.Bold
//            )
        }

    }

}

@Composable
fun SetEventButton(size: Dp, time: String, onClick: () -> Unit) {
    Button(modifier = Modifier.size(size),
        shape = CircleShape,
        onClick = { onClick() }) {
        Text(text = time, textAlign = TextAlign.Center)
    }
}

@Composable
fun PickTimeButton(size: Dp, onClick: () -> Unit) {
    IconButton(modifier = Modifier
        .then(Modifier.size(size))
        .border(MaterialTheme.spacing.extraSmall, Color.Transparent, shape = CircleShape),
        onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.Settings, contentDescription = "pick time",
            modifier = Modifier.size(size)
        )
    }
}


@Composable
fun PresetButton(
    time: Int, eventSize: Dp = 100.dp, pickTimeSize: Dp = 45.dp,
    onSetEventClick: () -> Unit, onPickTimeClick: () -> Unit
) {

    Box(contentAlignment = Alignment.BottomEnd) {
        SetEventButton(size = eventSize, time = time.toTimeString(),
            onClick = { onSetEventClick() })
        PickTimeButton(size = pickTimeSize, onClick = { onPickTimeClick() })
    }

}

@Composable
fun Iterators(modifier: Modifier = Modifier, size:Dp = 60.dp,
              isLeftActive:Boolean = true,
              isRightActive:Boolean = true,
              onLeftClick:()->Unit,
              onRightClick:()->Unit) {
    Row(modifier = modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(isLeftActive)
            Button(modifier = Modifier.size(size),
                shape = CircleShape,
                onClick = onLeftClick) {
                Text("<")
            }
        if(isRightActive)
            Button(modifier = Modifier.size(size),
                shape = CircleShape,
                onClick = onRightClick) {
                Text(">")
            }
    }
}


