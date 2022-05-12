package com.medically.core.progress

import com.medically.core.integration.Data
import com.medically.model.Chapter
import kotlinx.coroutines.launch

fun ChaptersProgressPort.bindChaptersProgress() {
    scope.launch {
        Data.chaptersRepository.getChaptersProgress().collect { chaptersList ->
            val chapters = chaptersList.groupBy { it.doctorName }
            state.value = state.value.copy(chaptersProgress = chapters)
        }
    }
}

fun ChaptersProgressPort.saveCurrentChapter(chapter: Chapter) {
    Data.chaptersRepository.setCurrentChapter(chapter)
}