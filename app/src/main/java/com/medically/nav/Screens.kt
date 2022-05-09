package com.medically.nav

/**
 * Screen metadata for Medically.
 */
enum class MedicallyScreens {
    MainScreen,
    SubjectDetailsScreen,
    LecturesScreen,
    DownLoadedLecturesScreen,
    AudioPlayerScreen;


    companion object {
        fun fromRoute(route: String?): MedicallyScreens =
            when (route?.substringBefore("/")) {
                MainScreen.name -> MainScreen
                SubjectDetailsScreen.name -> SubjectDetailsScreen
                DownLoadedLecturesScreen.name -> DownLoadedLecturesScreen
                LecturesScreen.name -> LecturesScreen
                AudioPlayerScreen.name -> AudioPlayerScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}