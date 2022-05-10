package com.medically.presentation.bookmark

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.bookmark.saveCurrentChapter
import com.medically.presentation.R
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.TransparentAppBar
import com.medically.presentation.component.list_with_header.ShimmerListWithHeader
import com.medically.presentation.downloaded_chapters.list.DownloadedChaptersList

@Composable
fun BookmarksScreen(goToLecturesScreen: () -> Unit) {
    val viewModel = viewModel<BookmarksViewModel>()
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TransparentAppBar(
                navigationIcon = null,
                title = "",
                subTitle = stringResource(id = R.string.bookmarks),
            )
        }
    ) {
        if (state.chapters.isEmpty() && !state.isLoading)
            EmptyTextMessage(iconId = R.drawable.ic_bookmark, message = R.string.no_bookmarks)

        if (state.isLoading)
            ShimmerListWithHeader()

        if (!state.isLoading && state.errorMessage == null)
            DownloadedChaptersList(
                chapters = state.chapters,
                onChapterSelected = {
                    viewModel.saveCurrentChapter(it)
                    goToLecturesScreen()
                }
            )
    }
}