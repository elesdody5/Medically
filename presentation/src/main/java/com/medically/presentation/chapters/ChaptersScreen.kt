package com.medically.presentation.chapters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.chapters.ChaptersPortState
import com.medically.core.chapters.bindChapters
import com.medically.presentation.R
import com.medically.presentation.chapters.component.ChaptersGrid
import com.medically.presentation.component.TransparentAppBar

@Composable
fun ChaptersScreen(goBack: () -> Unit) {
    val viewModel = viewModel<ChaptersViewModel>()
    val state: ChaptersPortState by viewModel.state.collectAsState()
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
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        if (!state.isLoading)
            ChaptersGrid(chapters = state.chapters)
    }
}

