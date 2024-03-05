package com.novelitech.firstcorountinekotlin

enum class LifecycleScopeScreens {
    ONE,
    TWO,
}

sealed class NavigationItem(val route: String) {
    object One : NavigationItem(LifecycleScopeScreens.ONE.name)
    object Two : NavigationItem(LifecycleScopeScreens.TWO.name)
}