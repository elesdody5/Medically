package com.medically.presentation.audio_player.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.R
import com.medically.presentation.component.CircleWithIcon
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun PlayerActions(
    modifier: Modifier = Modifier,
    changeSpeed: (Float) -> Unit,
    downloadAudio: () -> Unit,
    bookmarkAudio: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SpeedDropDown(changeSpeed = changeSpeed)
        Row(horizontalArrangement = Arrangement.spacedBy(19.dp)) {
            CircleWithIcon(
                circleSize = 34.dp,
                iconId = R.drawable.ic_download,
                contentDescription = "",
                modifier = Modifier.clickable { downloadAudio() }
            )
            CircleWithIcon(
                circleSize = 34.dp,
                iconId = R.drawable.ic_baseline_bookmark_border_24,
                contentDescription = "",
                modifier = Modifier.clickable { bookmarkAudio() }
            )
        }
    }
}

@Preview()
@Composable
fun PreviewPlayerActions() {
    MedicallyTheme {
        PlayerActions(changeSpeed = {}, downloadAudio = {}) {}
    }
}