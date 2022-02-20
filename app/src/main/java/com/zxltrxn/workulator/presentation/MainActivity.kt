package com.zxltrxn.workulator.presentation

import android.app.TimePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.media.VolumeShaper

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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.chargemap.compose.numberpicker.NumberPicker
import com.zxltrxn.workulator.R
import com.zxltrxn.workulator.domain.models.TaskModel
import com.zxltrxn.workulator.domain.models.TaskTimeModel
import com.zxltrxn.workulator.ui.AdditionTaskCard
import com.zxltrxn.workulator.ui.PickTimeCard
import com.zxltrxn.workulator.ui.ProgressScreen
import com.zxltrxn.workulator.ui.theme.elevation
import com.zxltrxn.workulator.ui.theme.spacing
import com.zxltrxn.workulator.ui.theme.WorkulatorTheme
import com.zxltrxn.workulator.utils.Constants.TAG
import com.zxltrxn.workulator.utils.toHoursMinutes
import com.zxltrxn.workulator.utils.toTimeString
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
                    ProgressScreen(viewModel = vm,
                        validateTask =::validateTask,validateTime = ::validateTime)
                }
            }
        }
    }

    private fun validateTask(time:Int,name:String):Boolean{
        return when{
            name.isEmpty()-> false
            time == 0 -> false
            else -> true
        }
    }

    private fun validateTime(time:Int):Boolean = validateTask(time = time,name = "a")

}
