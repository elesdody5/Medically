package com.medically.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransparentAppBar(
    goBack: () -> Unit,
    menuOptions: List<String>,
    menuPlaceHolder: String,
    onMenuSelect: (String) -> Unit,
    title: String,
    subTitle: String
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.height(80.dp)
    ) {
        IconButton(onClick = goBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colors.onBackground
            )
        }
        Column(modifier = Modifier.padding(end = 26.dp)) {
            Text(
                title,
                style = TextStyle(fontSize = 14.sp),
                color = MaterialTheme.colors.onBackground
            )
            Text(
                subTitle,
                modifier = Modifier.width(100.dp),
                style = TextStyle(fontSize = 22.sp),
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        DropDownMenu(
            options = menuOptions,
            placeHolder = menuPlaceHolder,
            onItemSelected = onMenuSelect
        )
    }
}