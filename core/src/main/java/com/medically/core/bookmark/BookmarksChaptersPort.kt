package com.medically.core.bookmark

import com.medically.core.entities.BusinessRule
import com.medically.core.entities.SelectedChapter
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import kotlinx.coroutines.flow.MutableStateFlow

interface BookmarksChaptersPort : PresentationPort<BookmarksChaptersState> {
    val bindBookmarksChapters: BusinessRule
}

data class BookmarksChaptersState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val selectionState: Boolean = false,
    val selectedChaptersCount: Int = 0,
    val chapters: Map<String, List<SelectedChapter>> = emptyMap(),
) : PresentationPortState()

infix fun MutableStateFlow<BookmarksChaptersState>.isLoading(isLoading: Boolean) {
    value = value.copy(isLoading = isLoading)
}