package com.medically.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.medically.main.component.MainBottomNavigation
import com.medically.main.component.MainNavHost

@Composable
fun MainScreen(medicallyNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MainBottomNavigation(navController = navController) },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavHost(navController = navController, medicallyNavController)
        }
    }
}