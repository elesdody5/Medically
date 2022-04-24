package com.medically.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medically.MedicallyScreens
import com.medically.MedicallyScreens.*
import com.medically.presentation.home.HomeScreen
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
                startDestination = HomeScreen.name
            ) {
                composable(HomeScreen.name) {
                    HomeScreen {
                        navController.navigate(SubjectDetailsScreen.name)
                    }
                }
                composable(
                    route = SubjectDetailsScreen.name,
                ) {
                    SubjectDetailsScreen {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}