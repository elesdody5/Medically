package com.medically

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medically.audioplayer.AudioPlayerScreen
import com.medically.ui.theme.MedicallyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicallyApp()
        }
    }
}

@Composable
fun MedicallyApp() {
    MedicallyTheme {
        val allScreens = MedicallyScreens.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = MedicallyScreens.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold { innerPadding ->
            NavHost(
                navController,
                modifier = Modifier.padding(innerPadding),
                startDestination = MedicallyScreens.AudioPlayer.name
            ) {
                composable(MedicallyScreens.AudioPlayer.name) {
                    AudioPlayerScreen()
                }
            }
        }
    }
}

