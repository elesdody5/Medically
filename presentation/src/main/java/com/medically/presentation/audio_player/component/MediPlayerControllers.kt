package com.medically.presentation.audio_player.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.ui.theme.MedicallyTheme
import com.medically.presentation.utils.timestampToMSS

@Composable
fun PlayerControllers(
    currentPosition: Long,
    duration: Long,
    isPlaying: Boolean,
    toggleState: () -> Unit,
    seekTo: (Long) -> Unit,
    skipForward: () -> Unit,
    skipBackward: () -> Unit,
    changeSpeed: (Float) -> Unit,
    downLoadAudio: () -> Unit,
    bookmarkAudio: () -> Unit,
) {
    val context = LocalContext.current
    val range = if (duration > 0f) 0f..duration.toFloat() else 0f..1f
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlayerButtons(
            Modifier.padding(vertical = 30.dp), isPlaying,
            toggleState, skipForward, skipBackward
        )
        Slider(
            value = currentPosition.toFloat(),
            valueRange = range,
            onValueChange = { seekTo(it.toLong()) },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp)
        ) {
            Text(
                text = currentPosition.timestampToMSS(context),
                style = TextStyle(fontSize = 10.sp)
            )
            Text(text = duration.timestampToMSS(context), style = TextStyle(fontSize = 10.sp))
        }

        PlayerActions(
            Modifier.padding(top = 15.dp),
            changeSpeed = changeSpeed,
            downLoadAudio,
            bookmarkAudio
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerController() {
    MedicallyTheme {
        PlayerControllers(
            0,
            0,
            true,
            toggleState = {},
            skipForward = {},
            skipBackward = {},
            seekTo = {},
            changeSpeed = {},
            downLoadAudio = {},
            bookmarkAudio = {})
    }
}

