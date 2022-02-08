package com.zxltrxn.workulator.presentation

import android.app.TimePickerDialog
import android.content.Context

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.chargemap.compose.numberpicker.NumberPicker
import com.zxltrxn.workulator.R
import com.zxltrxn.workulator.ui.elevation
import com.zxltrxn.workulator.ui.spacing
import com.zxltrxn.workulator.ui.theme.WorkulatorTheme
import com.zxltrxn.workulator.utils.Constants.TAG
import kotlinx.coroutines.Delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : ComponentActivity() {

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        lifecycleScope.launch { правильный подход
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
//            }
//        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            WorkulatorTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var additionState by remember{mutableStateOf(true)}
                    if(additionState){
                        AdditionTaskCard(validation = this::validateTask, onSave = { name,time ->
                            additionState = false

                        })
                    }
                }
            }
        }
    }

    fun validateTask(name:String,time:Int):Boolean{
        return when{
            name.isEmpty()-> false
            time == 0 -> false
            else -> true
        }
    }
}

@Composable
fun AdditionTaskCard(validation:(name:String,time:Int)->Boolean,
                     onSave:(name:String,time:Int)->Unit,
                     titleId:Int = R.string.new_task, taskName:String = "", time:Int = 0
){
    Box(contentAlignment = Alignment.BottomCenter,
    ) {
        val cornerShapePercent = 10
        val roundedShape = RoundedCornerShape(cornerShapePercent*2)
        val pickerTextStyle = MaterialTheme.typography.h2

        var hours by remember { mutableStateOf(time % 60) }
        var minutes by remember { mutableStateOf(time.rem(60)) }
        var name by rememberSaveable { mutableStateOf(taskName) }

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
            color = MaterialTheme.colors.background,
            elevation = MaterialTheme.elevation.large,
            shape = RoundedCornerShape(topStartPercent = cornerShapePercent,
                topEndPercent = cornerShapePercent)
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.large),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Text(style = MaterialTheme.typography.h1,text = stringResource(id = titleId))

                MyTextField(shape = roundedShape, value = name, onChange = {newName -> name = newName})

                Row(){
                    NumberPicker(value = hours, range = 0..149,textStyle = pickerTextStyle,
                        onValueChange = {
                            hours = it
                        }
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraLarge))

                    NumberPicker(value = minutes, range = (0..59).plus(0..59),
                        textStyle = pickerTextStyle,
                        onValueChange = {
                            minutes = it
                        }
                    )
                }

                Button(shape = roundedShape,
                    onClick = {
                        val curTime = minutes+hours*60
                        if(validation(name,curTime)){
                            onSave(name,curTime)
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.save), style = MaterialTheme.typography.button)
                }
            }
        }
    }
}
//, textChange:()->Unit
@Composable
fun MyTextField(shape: Shape, value:String, onChange:(String)->Unit
){
TextFieldDefaults
    TextField(modifier = Modifier
        .border(border = BorderStroke(width = MaterialTheme.spacing.extraSmall,
            color = MaterialTheme.colors.primary),
        shape = shape),
        value = value, onValueChange = { onChange(it)  },
        label ={Text(stringResource(id = R.string.enter_name_task))},
        placeholder = {Text(stringResource(id = R.string.name_task_placeholder))},
        shape = shape,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.background
        ),
        trailingIcon = {Icon(Icons.Filled.Clear, contentDescription = "clear",
            modifier = Modifier.offset(x= MaterialTheme.spacing.small).clickable {
            onChange("") // just send an update that the field is now empty
        })}
    )
}




// timePicker
//val hour = Calendar.HOUR_OF_DAY
//val minute = Calendar.MINUTE
//
//val targetTime = remember { mutableStateOf(0) }
//val timePickerDialog = TimePickerDialog(
//    context,
//    {_, hour : Int, minute: Int ->
//        targetTime.value = hour*60+minute
//    }, hour, minute, true
//)
//TextButton(onClick = { timePickerDialog.show() }) {
//    Text(stringResource(id = R.string.ask_target_time))
//}
//Text("${targetTime.value}")


//                    Box(contentAlignment =Alignment.Center,
//                        modifier = Modifier.fillMaxSize()){
//                        CircularProgressBar(percentage = 0.8f, number = 2400)
//                    }
@Composable
fun CircularProgressBar(
    percentage:Float,
    number:Int,
    fontSize:TextUnit = 28.sp,
    radius:Dp = 80.dp,
    color:Color = Color.Green,
    strokeWidth:Dp = 15.dp,
    animDuration:Int = 800,
    animDelay: Int = 0
){

    var animationPlayed by remember{
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true){
        animationPlayed = true
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ){
        Canvas(modifier = Modifier.size(radius * 2f)){
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 *curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(),cap = StrokeCap.Round)
            )
        }
        Text(text =(curPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}

//@Composable
//fun ShowTimePicker(context: Context){
//
//
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Text(text = "Selected Time: ${time.value}")
//        Spacer(modifier = Modifier.size(16.dp))
//        Button(onClick = {
//            timePickerDialog.show()
//        }) {
//            Text(text = "Open Time Picker")
//        }
//    }
//}


