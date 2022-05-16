package com.medically.presentation.lectures

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.lectures.*
import com.medically.presentation.R
import com.medically.presentation.component.ChapterOverflowMenu
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
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
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
        ObserveState(state, scaffoldState)
    }
}

@Composable
private fun ObserveState(
    state: LecturesPortState,
    scaffoldState: ScaffoldState
) {

    if (state.bookmarked != null) {
        val bookmark = stringResource(id = R.string.bookmarked)
        LaunchedEffect(key1 = state.bookmarked, block = {
            scaffoldState.snackbarHostState.showSnackbar(bookmark)
        })
    }
    if (state.downloadStart != null) {
        val downloadStart = stringResource(id = R.string.download_start)
        LaunchedEffect(key1 = state.downloadStart, block = {
            scaffoldState.snackbarHostState.showSnackbar(downloadStart)
        })
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
            ChapterOverflowMenu(
                downloadChapter = viewModel::downLoadChapter,
                bookmarkChapter = viewModel::bookmarkChapter
            )
        }
    )
}