package com.medically.presentation.lectures

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.lectures.LecturesPort
import com.medically.core.lectures.setCurrentAudioPlayList
import com.medically.presentation.component.LoadingProgress
import com.medically.presentation.component.TransparentAppBar
import com.medically.presentation.lectures.component.LecturesList

@Composable
fun LecturesScreen(
    goBack: () -> Boolean,
    goToAudioPlayer: () -> Unit,
    viewModel: LecturesPort = viewModel<LecturesViewModel>(),
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TransparentAppBar(
                goBack = goBack,
                subTitle = state.chapter?.doctorName ?: "",
                title = state.chapter?.name ?: ""
            )
        }
    ) {

        if (state.isLoading)
            LoadingProgress()
        if (!state.isLoading && state.lectures?.isNotEmpty() == true)
            LecturesList(state.lectures ?: emptyList()) {
                viewModel.setCurrentAudioPlayList(it)
                goToAudioPlayer()
            }
    }
}