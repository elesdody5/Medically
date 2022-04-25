package com.medically.presentation.audio_player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.model.Lecture
import com.medically.presentation.audio_player.component.LectureInfo
import com.medically.presentation.audio_player.component.PlayerControllers
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun AudioPlayerScreen(goBack: () -> Boolean) {
    val viewModel = viewModel<AudioViewModel>()
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 34.dp)
        ) {
            LectureInfo(imageUrl = "", lecture = Lecture("01", "Lecture"), doctorName = "")
            PlayerControllers()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAudioScreen() {
    MedicallyTheme {
        AudioPlayerScreen { true }
    }
}