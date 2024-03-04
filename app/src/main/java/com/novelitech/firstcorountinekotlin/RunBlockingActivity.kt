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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RunBlockingActivity : ComponentActivity() {

    val TAG = "RunBlockingActivity"

    /**
     * When calling 'delay' function it will not block the thread.
     * The difference between GlobalScope.launch(Dispatchers.Main) e runBlocking is that the last
     * one will block the Thread.Main when executing the delay function and I will not be able to
     * use the UI (to update).
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Before runBlocking")

        runBlocking {
            /**
             * If I have a suspend function and I want to call it inside the Main Thread I need to
             * use runBlocking.
             * Another use of this is for testing with Junit to actually access suspend function.
             * Used to figure out how corountine works behind the scene
             */

            // I can execute another corountine inside this block
            // And I don't need GlobalScope because I'm already inside a block
            // This block will run asynchronously inside the runBlocking corountine, so it won't be blocked
            // So both lunch block will execute in the same time and will not be blocked because it will
            // run in the Thread.IO and not in the Thread.Main that is blocked
            launch(Dispatchers.IO) {
                Log.d(TAG, "Started IO Corountine 1")
                delay(3000L)
                Log.d(TAG, "Finished IO Corountine 1")
            }

            launch(Dispatchers.IO) {
                Log.d(TAG, "Started IO Corountine 2")
                delay(2000L)
                Log.d(TAG, "Finished IO Corountine 2")
            }

            Log.d(TAG, "Start of runBlocking")
            delay(5000L)
            Log.d(TAG, "End of runBlocking")
        }

        Log.d(TAG, "After runBlocking")

        // This will execute after finishing the runBlocking execution
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