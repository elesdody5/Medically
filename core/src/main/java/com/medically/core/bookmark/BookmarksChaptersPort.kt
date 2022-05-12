package com.medically.core.bookmark

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import kotlinx.coroutines.flow.MutableStateFlow

interface BookmarksChaptersPort : PresentationPort<BookmarksChaptersState> {
    val bindBookmarksChapters: BusinessRule
}

data class BookmarksChaptersState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val chapters: Map<String, List<Chapter>> = emptyMap()
) : PresentationPortState()

infix fun MutableStateFlow<BookmarksChaptersState>.isLoading(isLoading: Boolean) {
    value = value.copy(isLoading = isLoading)
}