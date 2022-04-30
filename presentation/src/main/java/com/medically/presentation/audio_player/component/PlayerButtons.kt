package com.medically.presentation.audio_player.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    toggleStat: () -> Unit,
    skipForward: () -> Unit,
    skipBackward: () -> Unit
) {
    val playIcon =
        if (isPlaying) painterResource(id = R.drawable.ic_pause) else painterResource(id = R.drawable.ic_play)
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(stringResource(id = R.string.previous_time), style = TextStyle(fontSize = 10.sp))
        Image(
            painter = painterResource(id = R.drawable.ic_seck_backword),
            contentDescription = "backward",
            modifier = Modifier.clickable { skipBackward() }
        )
        IconButton(onClick = toggleStat) {
            Icon(
                painter = playIcon,
                contentDescription = "play",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_seck_forward),
            contentDescription = "forward",
            modifier = Modifier.clickable { skipForward() }

        )

        Text(stringResource(id = R.string.forward_time), style = TextStyle(fontSize = 10.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerButtons() {
    MedicallyTheme {
        PlayerButtons(isPlaying = true, toggleStat = {}, skipForward = {}) {}
    }
}