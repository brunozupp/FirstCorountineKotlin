package com.novelitech.firstcorountinekotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.novelitech.firstcorountinekotlin.ui.theme.FirstCorountineKotlinTheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class ExceptionHandlingActivity : ComponentActivity() {

    val TAG = "ExceptionHandlingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = CoroutineExceptionHandler { context, throwable ->
            Log.d(TAG, "Caught exception: $throwable")
        }

        CoroutineScope(Dispatchers.Main + handler).launch {

            // Using supervisorScope I guarantee that even if one launch fails the others will
            // not be canceled by it. If I don't use this and one of them fail then all the ohers
            // are canceled and the current Coroutine gets unavailable to receive another execution of sub coroutines scopes
            supervisorScope {
                launch {
                    delay(300L)
                    throw Exception("Coroutine 1 failed")
                }

                launch {
                    delay(400L)
                    Log.d(TAG, "Coroutine 2 finished")
                }
            }
        }

        setContent {

            FirstCorountineKotlinTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = TAG,
                        fontSize = 30.sp,
                    )

                }
            }
        }
    }
}