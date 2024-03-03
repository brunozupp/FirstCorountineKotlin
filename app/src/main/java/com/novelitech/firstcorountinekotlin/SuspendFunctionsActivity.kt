package com.novelitech.firstcorountinekotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.novelitech.firstcorountinekotlin.ui.theme.FirstCorountineKotlinTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuspendFunctionsActivity : ComponentActivity() {

    val TAG = "SuspendFunctionsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {

            // Suspend Functions = they can only be executed within another suspend function or
            // inside a corountine. "delay" function is an example of Suspend Function

            // They will be executed in the same time
            val networkCallAnswer = doNetworkCall()
            val networkCallAnswer2 = doNetworkCall2()

            Log.d(TAG, networkCallAnswer)
            Log.d(TAG, networkCallAnswer2)
        }

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

    suspend fun doNetworkCall() : String {
        delay(3000L)
        return "This is the answer"
    }

    suspend fun doNetworkCall2() : String {
        delay(3000L)
        return "This is the answer 2"
    }
}