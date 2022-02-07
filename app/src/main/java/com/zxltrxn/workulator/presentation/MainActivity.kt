package com.zxltrxn.workulator.presentation

import android.app.TimePickerDialog
import android.content.Context
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

        setContent {
            WorkulatorTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(contentAlignment = Alignment.CenterStart
                    ) {
                        AdditionTaskCard()
                    }
                }


            }
        }
    }
}

@Composable
fun AdditionTaskCard(){

        Surface(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
            color = MaterialTheme.colors.background,
            elevation = MaterialTheme.elevation.default,
            shape = MaterialTheme.shapes.medium
        ){

                Column(modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)
                    .fillMaxWidth(),
                ) {
                    var name by rememberSaveable { mutableStateOf("") }
                    Text(text = stringResource(id = R.string.enter_name_task))
                    TextField(value = name, onValueChange = { name = it },
                        placeholder = {Text(stringResource(id = R.string.name_task_placeholder))})

                }


        }


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


