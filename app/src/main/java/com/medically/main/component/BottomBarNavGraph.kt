package com.medically.main.component

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.medically.main.BottomBarScreens
import com.medically.nav.MedicallyScreens
import com.medically.presentation.home.HomeScreen

fun NavGraphBuilder.bottomBarNavGraph(
    medicallyNavController: NavHostController,
) {
    composable(BottomBarScreens.HomeScreen.name) {
        HomeScreen { medicallyNavController.navigate(MedicallyScreens.ChaptersScreen.name) }
    }
    composable(BottomBarScreens.Downloads.name) {
    }
    composable(BottomBarScreens.Bookmark.name) {
    }
    composable(BottomBarScreens.Progress.name) {

    }
}