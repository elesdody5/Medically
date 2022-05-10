package com.medically.presentation.progress

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.presentation.R
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.TransparentAppBar
import com.medically.presentation.progress.component.ChaptersProgressList

@Composable
fun ProgressScreen() {
    val viewModel = viewModel<ProgressViewModel>()
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar =
        {
            TransparentAppBar(
                title = "",
                subTitle = stringResource(id = R.string.progress),
                navigationIcon = null
            )
        }
    ) {
        if (state.chaptersProgress.isEmpty())
            EmptyTextMessage(iconId = R.drawable.progress, message = R.string.no_progress)

        if (state.chaptersProgress.isNotEmpty())
            ChaptersProgressList(state.chaptersProgress)
    }
}