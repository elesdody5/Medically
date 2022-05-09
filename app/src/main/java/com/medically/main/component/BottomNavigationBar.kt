package com.medically.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.medically.main.BottomBarScreens
import com.medically.presentation.ui.theme.Blue_0xff41486A

@Composable
fun MainBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomBarScreens.HomeScreen,
        BottomBarScreens.Downloads,
        BottomBarScreens.Bookmark,
        BottomBarScreens.Progress,
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Blue_0xff41486A,
        elevation = 0.dp,
        modifier = Modifier.background(shape = RoundedCornerShape(15.dp), color = Color.White)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            val selected = currentRoute == item.name
            BottomNavigationItem(selected = selected,
                onClick = { navController.navigate(item.name) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Blue_0xff41486A,
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.name,
                    )
                })
        }
    }
}