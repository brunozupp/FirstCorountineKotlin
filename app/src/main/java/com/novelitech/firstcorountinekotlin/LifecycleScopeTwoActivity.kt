package com.novelitech.firstcorountinekotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.novelitech.firstcorountinekotlin.ui.theme.FirstCorountineKotlinTheme

@Composable
fun LifecycleScopeTwoActivity(navController: NavHostController) {

    val TAG = "LifecycleScopeTwoActivity"

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