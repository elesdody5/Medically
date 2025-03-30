package com.medically.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        onDismissRequest = { showMenu = false },
        modifier = Modifier.width(150.dp)
    ) {
        DropdownMenuItem(
            modifier = Modifier.background(Color.White),
            onClick = {
                bookmarkChapter()
                showMenu = false
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "",
                modifier = Modifier.padding(end = 5.dp)
            )

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
                contentDescription = "",
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                stringResource(id = R.string.download),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}