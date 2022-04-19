package com.medically

/**
 * Screen metadata for Medically.
 */
enum class MedicallyScreens() {
    HomeScreen;


    companion object {
        fun fromRoute(route: String?): MedicallyScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}