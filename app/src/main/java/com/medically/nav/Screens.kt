package com.medically.nav

/**
 * Screen metadata for Medically.
 */
enum class MedicallyScreens {
    HomeScreen,
    SubjectDetailsScreen,
    LecturesScreen,
    ChaptersScreen;


    companion object {
        fun fromRoute(route: String?): MedicallyScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                SubjectDetailsScreen.name -> SubjectDetailsScreen
                ChaptersScreen.name -> ChaptersScreen
                LecturesScreen.name -> LecturesScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}