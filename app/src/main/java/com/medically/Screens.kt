package com.medically

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Screen metadata for Medically.
 */
enum class MedicallyScreens(
    val icon: ImageVector,
) {
    Overview(
        icon = Icons.Filled.PieChart,
    ),
    AudioPlayer(
        icon = Icons.Filled.Audiotrack,
    );


    companion object {
        fun fromRoute(route: String?): MedicallyScreens =
            when (route?.substringBefore("/")) {
                AudioPlayer.name -> AudioPlayer
                Overview.name -> Overview
                null -> Overview
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}