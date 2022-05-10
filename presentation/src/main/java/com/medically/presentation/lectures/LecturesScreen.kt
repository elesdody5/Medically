package com.medically.presentation.lectures

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.lectures.LecturesPort
import com.medically.core.lectures.LecturesPortState
import com.medically.core.lectures.bookmarkChapter
import com.medically.core.lectures.setCurrentAudioPlayList
import com.medically.presentation.R
import com.medically.presentation.component.LoadingProgress
import com.medically.presentation.component.OverflowMenu
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
            LecturesTopAppbar(goBack, state, viewModel)
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

@Composable
fun LecturesTopAppbar(
    goBack: () -> Boolean,
    state: LecturesPortState,
    viewModel: LecturesPort
) {
    TransparentAppBar(
        subTitle = state.chapter?.doctorName ?: "",
        title = state.chapter?.name ?: "",
        navigationIcon = {
            IconButton(modifier = Modifier, onClick = { goBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.onBackground,
                )
            }
        },
        actions = {
            OverflowMenu(
                text = stringResource(id = R.string.bookmark),
                onClick = viewModel::bookmarkChapter
            )
        }
    )
}