package com.medically.core.chapters

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data
import com.medically.model.Chapter


@BusinessRules
fun ChaptersPort.searchChapter(chapterName: String) {
    val chapters = state.value.chapters
    val filtered =
        chapters.filter { chapter -> chapter.name.contains(chapterName, ignoreCase = true) }
    state.value =
        state.value.copy(filteredChapters = filtered)
}

@BusinessRules
fun ChaptersPort.bindCurrentSubject() {
    val subject = Data.subjectsRepositoryPort.getCurrentSubject()
    state.value = state.value.copy(subject = subject)
}

@BusinessRules
fun ChaptersPort.saveCurrentChapter(chapter: Chapter) {
    Data.subjectDetailsRepository.setCurrentChapter(chapter)
}