package com.medically.presentation.audio_player.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun PlayerControllers() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlayerButtons(Modifier.padding(vertical = 30.dp))
        Slider(
            value = .5f, onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            Text(text = "10:00", style = TextStyle(fontSize = 10.sp))
            Text(text = "10:00", style = TextStyle(fontSize = 10.sp))
        }

        PlayerActions(Modifier.padding(top = 15.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerController() {
    MedicallyTheme {
        PlayerControllers()
    }
}

