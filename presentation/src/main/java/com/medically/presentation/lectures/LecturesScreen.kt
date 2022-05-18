package com.medically.presentation.lectures

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.lectures.*
import com.medically.presentation.R
import com.medically.presentation.component.ChapterOverflowMenu
import com.medically.presentation.component.LoadingProgress
import com.medically.presentation.component.RoundedAlertDialog
import com.medically.presentation.component.TransparentAppBar
import com.medically.presentation.lectures.component.LecturesList

@Composable
fun LecturesScreen(
    goBack: () -> Boolean,
    goToAudioPlayer: () -> Unit,
    viewModel: LecturesPort = viewModel<LecturesViewModel>(),
) {
    val state by viewModel.state.collectAsState()
    var openAlertDialog by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            LecturesTopAppbar(goBack, state, viewModel) { openAlertDialog = true }
        }
    ) {

        if (state.isLoading)
            LoadingProgress()
        if (!state.isLoading && state.lectures?.isNotEmpty() == true)
            LecturesList(state.lectures ?: emptyList()) {
                viewModel.setCurrentAudioPlayList(it)
                goToAudioPlayer()
            }
        ObserveState(
            state,
            scaffoldState,
            viewModel::downLoadChapter,
            openAlertDialog
        ) { openAlertDialog = false }
    }
}

@Composable
private fun ObserveState(
    state: LecturesPortState,
    scaffoldState: ScaffoldState,
    downloadChapter: () -> Unit,
    openAlert: Boolean,
    dismissAlert: () -> Unit
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
    if (openAlert) {
        RoundedAlertDialog(
            title = stringResource(id = R.string.download_chapter),
            buttonAction = downloadChapter,
            text = stringResource(id = R.string.download_chapter_message),
            dismissAlert = dismissAlert
        )
    }
}

@Composable
fun LecturesTopAppbar(
    goBack: () -> Boolean,
    state: LecturesPortState,
    viewModel: LecturesPort,
    openAlert: () -> Unit
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
                bookmarkChapter = viewModel::bookmarkChapter,
                downloadChapter = openAlert
            )
        }
    )
}