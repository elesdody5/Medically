package com.medically.main

import com.medically.presentation.R

enum class BottomBarScreens(val icon: Int) {
    HomeScreen(R.drawable.ic_home),
    Downloads(R.drawable.ic_downloads),
    Bookmark(R.drawable.ic_bookmark),
    Progress(R.drawable.progress);


    companion object {
        fun fromRoute(route: String?): BottomBarScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                Downloads.name -> Downloads
                Bookmark.name -> Bookmark
                Progress.name -> Progress
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}