package com.novelitech.firstcorountinekotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.novelitech.firstcorountinekotlin.ui.theme.FirstCorountineKotlinTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If the Main Thread terminates it execution, all the corountines will end up to (will be canceled)
        // When I call 'delay' function it will pause the current corountine and not blocking the whole thread
        // When I use the 'sleep' function in a Thread it will stop the whole Thread
        GlobalScope.launch {
            delay(3000L)
            Log.d(TAG, "Corountine says hello from thread ${Thread.currentThread().name}")
        }

        // Main
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")

        setContent {
            FirstCorountineKotlinTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Hello",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 30.sp,
                    )
                }
            }
        }


    }
}