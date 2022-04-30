package com.medically.presentation.audio_player

import android.content.ComponentName
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.player.*
import com.medically.media.service.MediaPlaybackService
import com.medically.media.service.MusicServiceConnection
import com.medically.presentation.audio_player.component.AudioTopBar
import com.medically.presentation.audio_player.component.LectureInfo
import com.medically.presentation.audio_player.component.PlayerControllers
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun AudioPlayerScreen(goBack: () -> Boolean) {
    val context = LocalContext.current
    val musicConnection = MusicServiceConnection.getInstance(
        context,
        ComponentName(context, MediaPlaybackService::class.java)
    )
    val factory = AudioViewModelFactory(musicConnection)
    val viewModel = viewModel<AudioViewModel>(factory = factory)
    val state by viewModel.state.collectAsState()
    val position by viewModel.mediaPosition.collectAsState()
    Scaffold(topBar = {
        AudioTopBar(goBack = goBack, title = state.currentChapter?.name ?: "")
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 34.dp)
        ) {
            LectureInfo(
                imageUrl = state.currentChapter?.imageUrl ?: "",
                lectureTitle = state.mediaMetadata?.title ?: "",
                doctorName = state.currentDoctor?.name ?: ""
            )
            PlayerControllers(
                position,
                state.mediaMetadata?.duration ?: 0,
                state.playbackState?.isPlaying ?: false,
                viewModel::toggleState,
                viewModel::seekTo,
                viewModel::skipForward,
                viewModel::skipBackward,
                viewModel::speed
            )
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