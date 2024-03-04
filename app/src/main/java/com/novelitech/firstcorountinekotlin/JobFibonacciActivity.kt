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

class JobFibonacciActivity : ComponentActivity() {

    val TAG = "JobFibonacciActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jobCancellingManually = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long running calculation...")

            for (i in 30..40) {

                /**
                 * I need to use isActive because as long as this is a complex and long calculation
                 * it will have no time to check the .cancel function as it has no delay function to
                 * stop it for a moment to be able to check
                 * In this way I need to manually check if the corountine is still alive
                 */
                if(isActive) {
                    Log.d(TAG, "Result for i = $i: ${fibbonaci(i)}")
                }
            }

            Log.d(TAG, "Ending long running calculation...")
        }

        runBlocking {
            delay(2000L)
            jobCancellingManually.cancel()
            Log.d(TAG, "Canceled job!")
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