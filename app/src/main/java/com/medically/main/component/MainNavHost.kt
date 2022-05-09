package com.medically.main.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.medically.main.BottomBarScreens

@Composable
fun MainNavHost(
    navController: NavHostController,
    medicallyNavController: NavHostController,
) {
    NavHost(
        navController,
        startDestination = BottomBarScreens.HomeScreen.name
    ) {
        bottomBarNavGraph(medicallyNavController)
    }
}