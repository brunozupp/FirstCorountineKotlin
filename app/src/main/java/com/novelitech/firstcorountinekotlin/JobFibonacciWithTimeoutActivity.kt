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
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class JobFibonacciWithTimeoutActivity : ComponentActivity() {

    val TAG = "JobFibonacciWithTimeoutActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long running calculation...")

            /**
             * Using withTimeout I can automatically cancel the block if the specific time I set
             * has passed
             * So withTimeout is actually equivalent to start a new corountine and delay it for 3 seconds
             * and then simply cancel the Job inside it.
             */
            withTimeout(3000L) {
                for (i in 30..40) {

                    /**
                     * I need to use isActive because as long as this is a complex and long calculation
                     * it will have no time to check the .cancel function as it has no delay function to
                     * stop it for a moment to be able to check
                     * In this way I need to manually check if the corountine is still alive.
                     * Even if I use withTimeout I still need to use isActive to check if the Job is
                     * alive
                     */
                    if(isActive) {
                        Log.d(TAG, "Result for i = $i: ${fibbonaci(i)}")
                    }
                }
            }

            Log.d(TAG, "Ending long running calculation...")
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

    fun fibbonaci(n: Int): Long {
        return if(n == 0) 0
        else if(n == 1) 1
        else fibbonaci(n - 1) + fibbonaci(n - 2)
    }
}