package com.medically.core.lectures

import com.medically.core.integration.Data
import com.medically.model.AudioPlayList
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

fun LecturesPort.bindOfflineLectures() {
    scope.launch {
        val chapter = state.value.chapter
        Data.lecturesRepository.getOfflineLectures(chapter?.name ?: "").collect {
            state.value = state.value.copy(isLoading = false, lectures = it)
        }

    }
}

fun LecturesPort.bindCurrentChapter() {
    val chapter = Data.chaptersRepository.currentChapter
    state.value = state.value.copy(chapter = chapter)
}


fun LecturesPort.setCurrentAudioPlayList(lecturePosition: Int) {
    val currentState = state.value
    val subject = Data.subjectsRepositoryPort.getCurrentSubject()
    val audioPlayList = AudioPlayList(
        currentState.lectures,
        lecturePosition,
        currentState.chapter,
        currentState.chapter?.doctorName,
        subject?.name
    )

    scope.launch {
        Data.lecturesRepository.setCurrentPlayList(audioPlayList)
    }
}