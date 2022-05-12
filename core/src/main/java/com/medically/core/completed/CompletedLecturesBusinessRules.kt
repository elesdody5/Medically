package com.medically.core.completed

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data
import kotlinx.coroutines.launch

@BusinessRules
fun CompletedLecturesPort.bindCompletedLectures() {
    state isLoading true
    scope.launch {
        val currentChapter = Data.chaptersRepository.currentChapter
        if (currentChapter != null) {
            Data.lecturesRepository.getCompletedLectures(currentChapter).collect {
                state.value = state.value.copy(lectures = it)
            }
        }
    }
}

@BusinessRules
fun CompletedLecturesPort.bindCurrentChapter() {
    val currentChapter = Data.chaptersRepository.currentChapter
    state.value = state.value.copy(chapter = currentChapter)
}