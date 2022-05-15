package com.medically.core.bookmark

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data
import com.medically.model.Chapter
import kotlinx.coroutines.launch

@BusinessRules
fun BookmarksChaptersPort.bindBookmarkedChapters() {
    state isLoading true
    scope.launch {
        Data.chaptersRepository.getBookmarksChapters().collect {
            val grouped = it.groupBy { chapter -> chapter.doctorName ?: "" }
            state.value = state.value.copy(isLoading = false, chapters = grouped)
        }
    }
}

@BusinessRules
fun BookmarksChaptersPort.saveCurrentChapter(chapter: Chapter) {
    Data.chaptersRepository.setCurrentChapter(chapter)
}