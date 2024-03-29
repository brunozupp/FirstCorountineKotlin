package com.novelitech.firstcorountinekotlin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.One.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.One.route) {
            LifecycleScopeOneActivity(navController)
        }
        composable(NavigationItem.Two.route) {
            LifecycleScopeTwoActivity(navController)
        }
    }
}