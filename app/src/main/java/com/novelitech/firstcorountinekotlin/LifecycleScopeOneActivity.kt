package com.novelitech.firstcorountinekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.novelitech.firstcorountinekotlin.ui.theme.FirstCorountineKotlinTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LifecycleScopeOneActivity(navController: NavHostController) {

    val TAG = "LifecycleScopeOneActivity"

    val coroutinesScope1 = rememberCoroutineScope()
    val coroutinesScope2 = rememberCoroutineScope()

    val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope

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
            Button(
                onClick = {

                    // It doesn't matter if I use the same corountine or another, it will stop
                    // the execution when I move to other screnn
//                    coroutinesScope1.launch {
//                        while (true) {
//                            delay(1000L)
//                            Log.d(TAG, "Still running...")
//                        }
//                    }

                    lifeCycleScope.launch {
                        while (true) {
                            delay(1000L)
                            Log.d(TAG, "Still running...")
                        }
                    }

                    coroutinesScope2.launch {
                        delay(5000L)

                        navController.navigate(LifecycleScopeScreens.TWO.name) {

                            // If I don't use popUpTo to take off LifecycleScopeOneActivity from the stack
                            // it will continue print "Still running..." because lifeCycleScope will run
                            // while the Composable stays alive
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                }
            ) {
                Text(text = "Click")
            }
        }
    }
}