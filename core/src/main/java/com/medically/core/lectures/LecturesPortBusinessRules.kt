package com.medically.core.lectures

import com.medically.core.integration.Data
import com.medically.model.Result
import kotlinx.coroutines.launch

fun LecturesPort.bindLectures() {
    state isLoading true
    scope.launch {
        val result = Data.lecturesRepository.getChapterLectures()
        if (result is Result.Success) {
            state.value = state.value.copy(isLoading = false, lectures = result.data)
        }
    }
}

fun LecturesPort.bindCurrentChapter() {
    val chapter = Data.subjectDetailsRepository.currentChapter
    state.value = state.value.copy(chapter = chapter)
}

fun LecturesPort.bindCurrentDoctor() {
    val doctor = Data.doctorsRepository.currentDoctor
    state.value = state.value.copy(doctor = doctor)
}