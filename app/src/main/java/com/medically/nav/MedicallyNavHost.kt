package com.medically.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medically.main.MainScreen
import com.medically.nav.MedicallyScreens.*
import com.medically.presentation.audio_player.AudioPlayerScreen
import com.medically.presentation.lectures.LecturesScreen
import com.medically.presentation.subject_details.SubjectDetailsScreen
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun MedicallyNavHost() {
    MedicallyTheme {
        val allScreens = values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = MedicallyScreens.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold { innerPadding ->
            NavHost(
                navController,
                modifier = Modifier.padding(innerPadding),
                startDestination = MainScreen.name
            ) {
                composable(MainScreen.name) {
                    MainScreen(navController)
                }
                composable(
                    route = SubjectDetailsScreen.name,
                ) {
                    SubjectDetailsScreen(
                        goBack = navController::popBackStack,
                        navigateToLectures = { navController.navigate(LecturesScreen.name) }
                    )
                }
                composable(LecturesScreen.name) {
                    LecturesScreen(goBack = navController::popBackStack) {
                        navController.navigate(AudioPlayerScreen.name)
                    }
                }
                composable(AudioPlayerScreen.name) {
                    AudioPlayerScreen(goBack = navController::popBackStack)
                }
            }
        }
    }
}