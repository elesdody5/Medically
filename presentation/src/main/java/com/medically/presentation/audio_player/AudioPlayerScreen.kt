package com.medically.presentation.audio_player

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.player.*
import com.medically.presentation.R
import com.medically.presentation.audio_player.component.AudioTopBar
import com.medically.presentation.audio_player.component.LectureInfo
import com.medically.presentation.audio_player.component.PlayerControllers
import com.medically.presentation.component.LoadingProgressDialog
import com.medically.presentation.component.RoundedAlertDialog
import com.medically.presentation.ui.theme.MedicallyTheme
import com.medically.presentation.utils.toMB

@Composable
fun AudioPlayerScreen(goBack: () -> Boolean) {
    val context = LocalContext.current
    val viewModel = viewModel<AudioViewModel>()
    val state by viewModel.state.collectAsState()
    val position by viewModel.mediaPosition.collectAsState()

    ObserveState(
        context,
        state,
        viewModel
    )

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
                viewModel::speed,
                viewModel::getLectureSize
            )
        }
    }
}

@Composable
private fun ObserveState(
    context: Context,
    currentState: PlayerPortState,
    viewModel: AudioViewModel,
) {
    if (currentState.downloadAlertVisibility) {
        RoundedAlertDialog(
            title = stringResource(id = R.string.download_alert_title),
            text = stringResource(
                id = R.string.download_alert_body,
                currentState.lectureSize.toMB(context)
            ),
            buttonAction = viewModel::downLoadAudio,
            dismissAlert = {
                viewModel.state.value = currentState.copy(downloadAlertVisibility = false)
            }
        )
    }

    if (currentState.isLoading) {
        LoadingProgressDialog()
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAudioScreen() {
    MedicallyTheme {
        AudioPlayerScreen { true }
    }
}