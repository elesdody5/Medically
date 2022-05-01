package com.medically.presentation.audio_player.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun AudioTopBar(goBack: () -> Boolean, title: String) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        IconButton(onClick = { goBack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            title,
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(fontSize = 22.sp),
            color = MaterialTheme.colors.onBackground,
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAudioTopBar() {
    MedicallyTheme {
        AudioTopBar(goBack = { true }, title = "Chapter")
    }
}