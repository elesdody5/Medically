package com.medically.nav

/**
 * Screen metadata for Medically.
 */
enum class MedicallyScreens {
    MainScreen,
    SubjectDetailsScreen,
    LecturesScreen,
    DownLoadedLecturesScreen,
    BookMarkedLecturesScreen,
    CompletedLecturesScreen,
    AudioPlayerScreen;


    companion object {
        fun fromRoute(route: String?): MedicallyScreens =
            when (route?.substringBefore("/")) {
                MainScreen.name -> MainScreen
                SubjectDetailsScreen.name -> SubjectDetailsScreen
                DownLoadedLecturesScreen.name -> DownLoadedLecturesScreen
                BookMarkedLecturesScreen.name -> BookMarkedLecturesScreen
                LecturesScreen.name -> LecturesScreen
                CompletedLecturesScreen.name -> CompletedLecturesScreen
                AudioPlayerScreen.name -> AudioPlayerScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}