package com.medically

/**
 * Screen metadata for Medically.
 */
enum class MedicallyScreens {
    HomeScreen,
    SubjectDetailsScreen,
    ChaptersScreen;


    companion object {
        fun fromRoute(route: String?): MedicallyScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                SubjectDetailsScreen.name -> SubjectDetailsScreen
                ChaptersScreen.name -> ChaptersScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}