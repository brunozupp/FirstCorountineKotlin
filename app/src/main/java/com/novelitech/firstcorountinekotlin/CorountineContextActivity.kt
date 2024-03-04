package com.novelitech.firstcorountinekotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class CorountineContextActivity : ComponentActivity() {

    val TAG = "CorountineContextActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // .Main -> will start a corountine inside Main Thread. This is useful when I need to do UI operations
        // from the corountine because I can only change the UI in the main Thread
//        GlobalScope.launch(Dispatchers.Main) {  }

        // .IO -> used to all kind of Data Operations such as Network, write to Databases or reading/writing to a file
//        GlobalScope.launch(Dispatchers.IO) {  }

        // Default -> used to do long stuff such as complex calculations that would block the main thread. One example
        // would be to sort a list of millions of items I would do this in the .Default to not block the Main Thread (Freeze the UI)
//        GlobalScope.launch(Dispatchers.Default) {  }

        // Unfonfined meaning = not kept in a limited space; allowed to move or grow freely:
        // .Unconfined -> If you start a coroutine and unconfined that, it causes suspend function
        // to stay in the thread that the suspend function resumed
        // .Unconfined following the documentation = A coroutine dispatcher that is not confined to
        // any specific thread. It executes the initial continuation of a coroutine in the current
        // call-frame and lets the coroutine resume in whatever thread that is used by the
        // corresponding suspending function, without mandating any specific threading policy.
        // Nested coroutines launched in this dispatcher form an event-loop to avoid stack overflows.
//        GlobalScope.launch(Dispatchers.Unconfined) {  }

        // newSingleThreadContext("Name the Thread") = Running a corountine inside a new Thread
//        GlobalScope.launch(newSingleThreadContext("MyThread")) { }



        setContent {

            var text by remember {
                mutableStateOf("CorountineContextActivity")
            }

            FirstCorountineKotlinTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = text,
                        fontSize = 30.sp,
                    )
                    Button(
                        onClick = {

                            // Example of how to run 2 different Dispatchers inside a function
                            // In this case I fake a call in the API with doNetworkCall() using de .IO
                            // and then with the result I use de .Main to update de UI
                            GlobalScope.launch(Dispatchers.IO) {

                                Log.d(TAG, "Starting corountine inside thread = ${Thread.currentThread().name}")

                                val answer = doNetworkCall()

                                withContext(Dispatchers.Main) {

                                    Log.d(TAG, "Starting corountine inside thread = ${Thread.currentThread().name}")

                                    text = answer
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text(
                            text = "Click",
                            fontSize = 30.sp,
                        )
                    }
                }
            }
        }
    }

    suspend fun doNetworkCall() : String {
        delay(3000L)
        return "This is the answer"
    }
}