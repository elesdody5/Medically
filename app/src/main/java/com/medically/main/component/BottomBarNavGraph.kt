package com.medically.main.component

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.medically.main.BottomBarScreens
import com.medically.nav.MedicallyScreens
import com.medically.presentation.bookmark.BookmarksScreen
import com.medically.presentation.downloaded_chapters.DownLoadScreen
import com.medically.presentation.home.HomeScreen
import com.medically.presentation.progress.ProgressScreen

fun NavGraphBuilder.bottomBarNavGraph(
    medicallyNavController: NavHostController,
) {
    composable(BottomBarScreens.HomeScreen.name) {
        HomeScreen { medicallyNavController.navigate(MedicallyScreens.SubjectDetailsScreen.name) }
    }
    composable(BottomBarScreens.Downloads.name) {
        DownLoadScreen { medicallyNavController.navigate(MedicallyScreens.DownLoadedLecturesScreen.name) }
    }
    composable(BottomBarScreens.Bookmark.name) {
        BookmarksScreen { medicallyNavController.navigate(MedicallyScreens.BookMarkedLecturesScreen.name) }
    }

    composable(BottomBarScreens.Progress.name) {
        ProgressScreen()
    }
}