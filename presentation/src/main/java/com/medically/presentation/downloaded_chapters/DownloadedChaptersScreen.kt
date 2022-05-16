package com.medically.presentation.downloaded_chapters

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.offline.chapters.clearSelected
import com.medically.core.offline.chapters.removeSelectedChapters
import com.medically.core.offline.chapters.saveCurrentChapter
import com.medically.core.offline.chapters.selectChapter
import com.medically.presentation.R
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.list_with_header.ShimmerListWithHeader
import com.medically.presentation.component.topAppBar
import com.medically.presentation.downloaded_chapters.list.OfflineChaptersList

@Composable
fun DownLoadScreen(goToDownLoadLectures: () -> Unit) {
    val viewModel = viewModel<DownloadedChaptersViewModel>()
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = topAppBar(
            title = stringResource(id = R.string.downloads),
            selectionState = state.selectionState,
            selectedCount = state.selectedChaptersCount,
            clearSelection = viewModel::clearSelected,
            deleteSelected = viewModel::removeSelectedChapters
        )
    ) {
        if (state.chapters.isEmpty() && !state.isLoading)
            EmptyTextMessage(iconId = R.drawable.ic_downloads, message = R.string.no_downloads)

        if (state.isLoading)
            ShimmerListWithHeader()

        if (!state.isLoading && state.errorMessage == null)
            OfflineChaptersList(
                chapters = state.chapters,
                onChapterClicked = {
                    if (!state.selectionState) {
                        viewModel.saveCurrentChapter(it)
                        goToDownLoadLectures()
                    } else {
                        viewModel.selectChapter(it)
                    }
                },
                onChapterLongPress = viewModel::selectChapter,
                selectionState = state.selectionState
            )
    }
}