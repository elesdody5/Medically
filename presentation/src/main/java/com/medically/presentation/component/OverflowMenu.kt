package com.medically.presentation.component

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun OverflowMenu(text: String, onClick: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    IconButton(onClick = {
        showMenu = !showMenu
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "more",
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            modifier = Modifier.background(Color.White),
            onClick = {
                onClick()
                showMenu = false
            }
        ) {
            Text(
                text,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}