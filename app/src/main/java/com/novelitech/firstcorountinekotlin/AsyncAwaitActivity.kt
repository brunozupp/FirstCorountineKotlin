package com.novelitech.firstcorountinekotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.novelitech.firstcorountinekotlin.ui.theme.FirstCorountineKotlinTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

// Link Resource = https://medium.com/@husayn.fakher/exploring-the-differences-between-launch-and-async-in-kotlin-coroutines-10-questions-answered-4b7c2280e87f
class AsyncAwaitActivity : ComponentActivity() {

    val TAG = "AsyncAwaitActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {

            // This serves to measure the total time of execution from this block of code
            val time = measureTimeMillis {

                // When I want something to run asynchronously I need to use async
                // The computations inside the async block will execute concurrently, and the async
                // coroutine builder immediately returns a Deferred object without blocking the
                // calling thread. This allows other operations to continue while the coroutine
                // is running in the background.
                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }

                // .await will Block/Suspend the current corountine until the answers are available
                // await should always be used from within a coroutine or a suspending function.
                Log.d(TAG, "Answer 1 is ${answer1.await()}")
                Log.d(TAG, "Answer 2 is ${answer2.await()}")
            }

            Log.d(TAG, "Time is = $time ms")

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

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer 2"
    }
}