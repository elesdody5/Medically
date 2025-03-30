package com.medically.presentation.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.medically.model.Video

@Composable
fun VideosMenu(
    videos: List<Video>,
    onVideoSelected: (Video) -> Unit
) {
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
        onDismissRequest = { showMenu = false },
        modifier = Modifier.width(150.dp)
    ) {
        videos.forEach { video ->
            DropdownMenuItem(
                modifier = Modifier.background(Color.White),
                onClick = {
                    onVideoSelected(video)
                    showMenu = false
                }
            ) {
                Text(
                    video.name ?: "",
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}