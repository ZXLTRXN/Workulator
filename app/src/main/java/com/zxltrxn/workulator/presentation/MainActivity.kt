package com.zxltrxn.workulator.presentation

import android.app.TimePickerDialog
import android.content.Context

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
import com.zxltrxn.workulator.ui.AdditionTaskCard
import com.zxltrxn.workulator.ui.PickTimeCard
import com.zxltrxn.workulator.ui.theme.elevation
import com.zxltrxn.workulator.ui.theme.spacing
import com.zxltrxn.workulator.ui.theme.WorkulatorTheme
import com.zxltrxn.workulator.utils.Constants.TAG
import com.zxltrxn.workulator.utils.toast
import kotlinx.coroutines.Delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch { правильный подход
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
//            }
//        }
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE )

        setContent {
            val vm by viewModel<MainViewModel>()
            WorkulatorTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var additionState by rememberSaveable{mutableStateOf(true)}

//                    if(additionState){
//                        AdditionTaskCard(height = 0.55f, validation = ::validateTask, onSave = { time,name ->
//                            additionState = false
//                            Log.d(TAG, "onCreate addTask: $time $name")
//                        })
//                    }

                    PickTimeCard(height = 0.4f,validation = ::validateTask, onSave = {
                        Log.d(TAG, "onCreate pick time: $it")
                    })
                }
            }
        }
    }

    private fun validateTask(time:Int,name:String = "а"):Boolean{
        return when{
            name.isEmpty()-> false
            time == 0 -> false
            else -> true
        }
    }
}

@Composable
fun ProgressScreen(){

}

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


