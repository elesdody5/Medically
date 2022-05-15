package com.medically.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.medically.presentation.R

@Composable
fun ChapterOverflowMenu(
    downloadChapter: () -> Unit,
    bookmarkChapter: () -> Unit
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
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            modifier = Modifier.background(Color.White),
            onClick = {
                bookmarkChapter()
                showMenu = false
            }
        ) {
            Text(
                stringResource(id = R.string.bookmark),
                color = MaterialTheme.colors.onBackground
            )
        }

        DropdownMenuItem(
            modifier = Modifier.background(Color.White),
            onClick = {
                downloadChapter()
                showMenu = false
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = ""
            )
            Text(
                stringResource(id = R.string.download),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}