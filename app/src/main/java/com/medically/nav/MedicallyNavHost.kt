package com.medically.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medically.main.MainScreen
import com.medically.nav.MedicallyScreens.*
import com.medically.presentation.audio_player.AudioPlayerScreen
import com.medically.presentation.bookmark_lectures.BookmarkedLecturesViewModel
import com.medically.presentation.completed_lectures.CompletedLecturesScreen
import com.medically.presentation.downloaded_lectures.DownLoadedLecturesViewModel
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
                    LecturesScreen(goBack = navController::popBackStack, goToAudioPlayer = {
                        navController.navigate(AudioPlayerScreen.name)
                    })
                }
                composable(AudioPlayerScreen.name) {
                    AudioPlayerScreen(goBack = navController::popBackStack)
                }

                composable(DownLoadedLecturesScreen.name) {
                    LecturesScreen(goBack = navController::popBackStack,
                        viewModel = viewModel<DownLoadedLecturesViewModel>(),
                        goToAudioPlayer = {
                            navController.navigate(AudioPlayerScreen.name)
                        })
                }

                composable(BookMarkedLecturesScreen.name) {
                    LecturesScreen(goBack = navController::popBackStack,
                        viewModel = viewModel<BookmarkedLecturesViewModel>(),
                        goToAudioPlayer = {
                            navController.navigate(AudioPlayerScreen.name)
                        })
                }
                composable(CompletedLecturesScreen.name) {
                    CompletedLecturesScreen { navController.popBackStack() }
                }
            }
        }
    }
}