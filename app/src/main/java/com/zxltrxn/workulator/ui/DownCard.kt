package com.zxltrxn.workulator.ui

import android.widget.TextClock
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.zIndex
import com.chargemap.compose.numberpicker.NumberPicker
import com.zxltrxn.workulator.R
import com.zxltrxn.workulator.ui.theme.elevation
import com.zxltrxn.workulator.ui.theme.spacing
import com.zxltrxn.workulator.utils.toHoursMinutes
import com.zxltrxn.workulator.utils.toast

@Composable
fun DownCardBox(height:Float,
                content: @Composable ColumnScope.() -> Unit
){
    Box(contentAlignment = Alignment.BottomCenter
    ){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(height),
            color = MaterialTheme.colors.background,
            elevation = MaterialTheme.elevation.large,
            shape = MaterialTheme.shapes.large
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.large),
                content = content,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            )
        }
    }
}

@Composable
fun PickTimeCard(validation:(time:Int)->Boolean,
                 onSave:(time:Int)->Unit,
                 onBack:()->Unit,
                 height: Float,
                 time:Int = 0
){
    val context = LocalContext.current
    val t = time.toHoursMinutes()
    var hours by rememberSaveable{ mutableStateOf(t[0]) }
    var minutes by rememberSaveable { mutableStateOf(t[1]) }

    DownCardBox(height = height) {

        Text(style = MaterialTheme.typography.h1,text = stringResource(id = R.string.ask_target_time))

        MyDatePicker(hours = hours, minutes = minutes,
            onChangeHours = { hours = it }, onChangeMinutes = { minutes = it })

        val validError = stringResource(id = R.string.no_valid_task_input_data)
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            TextButton(onClick = { onBack() }) {
                Text(text = stringResource(id = R.string.go_back),
                    style = MaterialTheme.typography.button)
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

            Button(shape = MaterialTheme.shapes.medium,
                onClick = {
                    val curTime = minutes + hours * 60
                    if (validation(curTime)) {
                        onSave(curTime)
                    } else {
                        context.toast(validError, Toast.LENGTH_SHORT)
                    }
                }
            ) {
                Text(stringResource(id = R.string.save), style = MaterialTheme.typography.button)
            }

        }
    }
}

@Composable
fun AdditionTaskCard(validation:(time:Int, name:String)->Boolean,
                     onSave:(time:Int, name:String)->Unit,
                     onBack:()->Unit,
                     height:Float, titleId:Int = R.string.add_task,
                     taskName:String = "", time:Int = 0
){
    DownCardBox(height = height
    ) {
        val context = LocalContext.current
        val validError = stringResource(id = R.string.no_valid_task_input_data)
        val t = time.toHoursMinutes()
        var hours by remember { mutableStateOf(t[0]) }
        var minutes by remember { mutableStateOf(t[1]) }
        var name by rememberSaveable { mutableStateOf(taskName) }

        Text(style = MaterialTheme.typography.h1,text = stringResource(id = titleId))

        MyTextField(shape = MaterialTheme.shapes.medium, value = name, onChange = { newName -> name = newName})

        MyDatePicker(hours = hours,minutes = minutes,
            onChangeHours = {hours = it}, onChangeMinutes = {minutes = it})

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            TextButton(onClick = { onBack() }) {
                Text(
                    text = stringResource(id = R.string.go_back),
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

            Button(shape = MaterialTheme.shapes.medium,
                onClick = {
                    val curTime = minutes + hours * 60
                    if (validation(curTime, name)) {
                        onSave(curTime, name)
                    } else {
                        context.toast(validError, Toast.LENGTH_SHORT)
                    }
                }
            ) {
                Text(stringResource(id = R.string.save), style = MaterialTheme.typography.button)
            }
        }
    }
}

@Composable
fun MyDatePicker(hours:Int,minutes:Int,
                 onChangeHours: (Int) -> Unit,
                 onChangeMinutes: (Int) -> Unit,
                 hoursRange:IntRange = 0..149,
                 minutesRange:IntRange = 0..59,
                 textStyle: TextStyle = MaterialTheme.typography.h2
){
    Row(verticalAlignment = Alignment.CenterVertically){
        NumberPicker(value = hours, range = hoursRange,textStyle = textStyle,
            onValueChange = { onChangeHours(it) }
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(stringResource(R.string.Hours))
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraLarge))

        NumberPicker(value = minutes, range = (minutesRange).plus(minutesRange),
            textStyle = textStyle,
            onValueChange = {onChangeMinutes(it)}
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(stringResource(R.string.Minutes))
    }
}

@Composable
fun MyTextField(shape: Shape, value:String, onChange:(String)->Unit
){
    val focusManager = LocalFocusManager.current

    TextField(modifier = Modifier
        .border(border = BorderStroke(width = MaterialTheme.spacing.extraSmall,
            color = MaterialTheme.colors.primary),
            shape = shape),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Sentences),
        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
        value = value, onValueChange = { onChange(it)  },
        label ={ Text(stringResource(id = R.string.enter_name_task)) },
        placeholder = { Text(stringResource(id = R.string.name_task_placeholder)) },
        shape = shape,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.background
        ),
        trailingIcon = {
            Icon(Icons.Filled.Clear, contentDescription = "clear",
            modifier = Modifier
                .offset(x = MaterialTheme.spacing.small)
                .clickable {
                    onChange("")
                })
        }
    )
}