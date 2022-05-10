package com.medically.core.offline.chapters

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data
import com.medically.model.Chapter
import kotlinx.coroutines.launch

@BusinessRules
fun OfflineChaptersPort.bindOfflineChapters() {
    state isLoading true
    scope.launch {
        Data.chaptersRepository.getOfflineChapters().collect {
            val grouped = it.groupBy { chapter -> chapter.doctorName ?: "" }
            state.value = state.value.copy(isLoading = false, chapters = grouped)
        }
    }
}

@BusinessRules
fun OfflineChaptersPort.saveCurrentChapter(chapter: Chapter) {
    Data.chaptersRepository.setCurrentChapter(chapter)
}