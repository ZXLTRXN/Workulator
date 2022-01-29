package com.zxltrxn.workulator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zxltrxn.workulator.ui.theme.WorkulatorTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Composable
//fun MainScreen() {
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    var currentTimeFlow by remeber{mutableStateOf("")}
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkulatorTheme {
        Greeting("Android")
    }
}