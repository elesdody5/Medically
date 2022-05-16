package com.medically.presentation.bookmark

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.bookmark.clearSelected
import com.medically.core.bookmark.removeSelectedChapters
import com.medically.core.bookmark.saveCurrentChapter
import com.medically.core.bookmark.selectChapter
import com.medically.presentation.R
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.list_with_header.ShimmerListWithHeader
import com.medically.presentation.component.topAppBar
import com.medically.presentation.downloaded_chapters.list.OfflineChaptersList

@Composable
fun BookmarksScreen(goToLecturesScreen: () -> Unit) {
    val viewModel = viewModel<BookmarksViewModel>()
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = topAppBar(
            title = stringResource(id = R.string.bookmarks),
            selectionState = state.selectionState,
            selectedCount = state.selectedChaptersCount,
            clearSelection = viewModel::clearSelected,
            deleteSelected = viewModel::removeSelectedChapters
        )
    ) {
        if (state.chapters.isEmpty() && !state.isLoading)
            EmptyTextMessage(iconId = R.drawable.ic_bookmark, message = R.string.no_bookmarks)

        if (state.isLoading)
            ShimmerListWithHeader()

        if (!state.isLoading && state.errorMessage == null)
            OfflineChaptersList(
                chapters = state.chapters,
                onChapterClicked = {
                    if (!state.selectionState) {
                        viewModel.saveCurrentChapter(it)
                        goToLecturesScreen()
                    } else {
                        viewModel.selectChapter(it)
                    }
                },
                onChapterLongPress = {
                    viewModel.selectChapter(it)
                },
                selectionState = state.selectionState
            )
    }
}