package com.medically.core.chapters

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data
import com.medically.model.Result
import com.medically.model.Subject
import kotlinx.coroutines.launch

@BusinessRules
fun ChaptersPort.bindChapters(doctorName: String) {
    state.value = state.value.copy(isLoading = true)
    scope.launch {
        val result = Data.chaptersRepository.getChapters(doctorName)
        if (result is Result.Success) {
            state.value = state.value.copy(
                isLoading = false,
                chapters = result.data,
                filteredChapters = result.data.toList()
            )
        }
    }
}

@BusinessRules
fun ChaptersPort.bindDoctors() {
    state.value = state.value.copy(isLoading = true)
    scope.launch {
        val result = Data.doctorsRepository.getDoctors()
        if (result is Result.Success) {
            state.value = state.value.copy(
                isLoading = false,
                doctors = result.data
            )
            bindChapters(result.data.first().name ?: "")
        }
    }
}

@BusinessRules
fun ChaptersPort.searchChapter(chapterName: String) {
    val chapters = state.value.chapters
    val filtered =
        chapters.filter { chapter -> chapter.name.contains(chapterName, ignoreCase = true) }
    state.value =
        state.value.copy(filteredChapters = filtered)
}

@BusinessRules
fun ChaptersPort.bindChaptersSubject() {
    val subject = Data.subjectsRepositoryPort.getCurrentSubject()
    state.value = state.value.copy(subject = subject)
}