package com.medically.core.offline.chapters

import com.medically.core.entities.BusinessRules
import com.medically.core.entities.SelectedChapter
import com.medically.core.integration.Data
import kotlinx.coroutines.launch

@BusinessRules
fun OfflineChaptersPort.bindOfflineChapters() {
    state isLoading true
    scope.launch {
        Data.chaptersRepository.getOfflineChapters().collect {
            val grouped = it.map { chapter -> SelectedChapter(chapter = chapter) }
                .groupBy { selectedChapter -> selectedChapter.chapter.doctorName }
            state.value = state.value.copy(isLoading = false, chapters = grouped)
        }
    }
}

@BusinessRules
fun OfflineChaptersPort.saveCurrentChapter(selectedChapter: SelectedChapter) {
    Data.chaptersRepository.setCurrentChapter(selectedChapter.chapter)
}

@BusinessRules
fun OfflineChaptersPort.clearSelected() {
    state.value = state.value.copy(selectionState = false, selectedChaptersCount = 0)
    state.value.chapters.values.flatten().forEach { it.isSelected = false }
}

@BusinessRules
fun OfflineChaptersPort.selectChapter(selectedChapter: SelectedChapter) {
    var currentSelected = state.value.selectedChaptersCount
    if (selectedChapter.isSelected) {
        selectedChapter.isSelected = false
        currentSelected -= 1
    } else {
        selectedChapter.isSelected = true
        currentSelected += 1
    }
    state.value = state.value.copy(selectionState = true, selectedChaptersCount = currentSelected)
}

@BusinessRules
fun OfflineChaptersPort.removeSelectedChapters() {
    scope.launch {
        state isLoading false
        val selectedChapters =
            state.value.chapters.values.flatten().filter { it.isSelected }.map { it.chapter }
        Data.chaptersRepository.removeOfflineChapters(*selectedChapters.toTypedArray())
        bindOfflineChapters()
    }
}