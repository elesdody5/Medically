package com.medically.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.bookmark.BookmarksChaptersPort
import com.medically.core.bookmark.BookmarksChaptersState
import com.medically.core.bookmark.bindBookmarkedChapters
import kotlinx.coroutines.flow.MutableStateFlow

class BookmarksViewModel : ViewModel(), BookmarksChaptersPort {
    override val scope = viewModelScope
    override val state = MutableStateFlow(BookmarksChaptersState())
    override val bindBookmarksChapters = bindBookmarkedChapters()
}