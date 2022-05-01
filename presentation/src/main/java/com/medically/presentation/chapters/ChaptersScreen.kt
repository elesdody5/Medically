package com.medically.presentation.chapters

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.chapters.saveCurrentChapter
import com.medically.model.Chapter
import com.medically.presentation.R
import com.medically.presentation.chapters.component.ChaptersGrid
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.LoadingProgress

@Composable
fun ChaptersScreen(
    isLoading: Boolean,
    chapters: List<Chapter>,
    navigateToLectures: () -> Unit
) {
    val viewModel = viewModel<ChaptersViewModel>()
    if (isLoading)
        LoadingProgress()
    if (chapters.isEmpty() && !isLoading)
        EmptyTextMessage(iconId = R.drawable.ic_audo, message = R.string.no_audio)
    if (!isLoading)
        ChaptersGrid(chapters = chapters) {
            viewModel.saveCurrentChapter(it)
            navigateToLectures()
        }

}

