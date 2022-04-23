package com.medically.nav

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.medically.MedicallyScreens
import com.medically.MedicallyScreens.*
import com.medically.model.Subject
import com.medically.presentation.chapters.ChaptersScreen
import com.medically.presentation.home.HomeScreen
import com.medically.presentation.ui.theme.MedicallyTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun MedicallyNavHost() {
    MedicallyTheme {
        val allScreens = MedicallyScreens.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = MedicallyScreens.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold { innerPadding ->
            NavHost(
                navController,
                modifier = Modifier.padding(innerPadding),
                startDestination = HomeScreen.name
            ) {
                composable(HomeScreen.name) {
                    HomeScreen {
                        navController.navigate(ChaptersScreen.name)
                    }
                }
                composable(
                    route = ChaptersScreen.name,
                ) {
                    ChaptersScreen() {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}