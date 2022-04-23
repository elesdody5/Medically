package com.medically.presentation.chapters

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.chapters.bindChapters
import com.medically.presentation.R
import com.medically.presentation.chapters.component.ChaptersGrid
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.LoadingProgress
import com.medically.presentation.component.TransparentAppBar

@Composable
fun ChaptersScreen(goBack: () -> Unit) {
    val viewModel = viewModel<ChaptersViewModel>()
    val state by viewModel.state.collectAsState()
    val doctors = state.doctors.map { it?.name ?: "" }.toList()
    Scaffold(
        topBar = {
            TransparentAppBar(
                goBack,
                doctors,
                stringResource(id = R.string.doctor),
                viewModel::bindChapters,
                state.subject?.yearName ?: "",
                state.subject?.name ?: ""
            )
        }
    ) {
        if (state.isLoading)
            LoadingProgress()
        if (state.chapters.isEmpty() && !state.isLoading)
            EmptyTextMessage(iconId = R.drawable.ic_audo, message = R.string.no_audio)
        if (!state.isLoading)
            ChaptersGrid(chapters = state.chapters, it)
    }
}

