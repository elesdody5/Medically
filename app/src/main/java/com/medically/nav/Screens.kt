package com.medically.nav

/**
 * Screen metadata for Medically.
 */
enum class MedicallyScreens {
    HomeScreen,
    SubjectDetailsScreen,
    LecturesScreen,
    ChaptersScreen,
    AudioPlayerScreen;


    companion object {
        fun fromRoute(route: String?): MedicallyScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                SubjectDetailsScreen.name -> SubjectDetailsScreen
                ChaptersScreen.name -> ChaptersScreen
                LecturesScreen.name -> LecturesScreen
                AudioPlayerScreen.name -> AudioPlayerScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}