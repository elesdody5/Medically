package com.medically.core.lectures

import com.medically.core.integration.Data
import com.medically.core.integration.Framework
import com.medically.model.AudioPlayList
import com.medically.model.Chapter
import com.medically.model.Result
import kotlinx.coroutines.launch

fun LecturesPort.bindLectures() {
    state isLoading true
    scope.launch {
        val result = Data.lecturesRepository.getChapterLectures()
        if (result is Result.Success) {
            state.value = state.value.copy(isLoading = false, lectures = result.data)
            insertChapterProgress(result.data?.size ?: 0)
        }
    }
}

fun LecturesPort.bindOfflineLectures() {
    scope.launch {
        val chapter = state.value.chapter
        chapter?.let {
            Data.lecturesRepository.getOfflineLectures(it).collect { list ->
                state.value = state.value.copy(isLoading = false, lectures = list)
            }
        }

    }
}

fun LecturesPort.bindBookmarkLectures() {
    scope.launch {
        val chapter = state.value.chapter
        chapter?.let {
            Data.lecturesRepository.getBookmarkedLectures(it).collect { list ->
                state.value = state.value.copy(isLoading = false, lectures = list)
            }
        }

    }
}

fun LecturesPort.downLoadChapter() {
    val downloader = Framework.downLoaderManager
    val lectures = state.value.lectures
    val chapter = state.value.chapter
    if (lectures != null && chapter != null) {
        downloader.downLoad(lectures, chapter)
        state.value = state.value.copy(downloadStart = true)
    }
}

fun LecturesPort.bindCurrentChapter() {
    val chapter = Data.chaptersRepository.currentChapter
    state.value = state.value.copy(chapter = chapter)
}

fun LecturesPort.bookmarkChapter() {
    val chapter = Data.chaptersRepository.currentChapter
    val lectures = state.value.lectures
    if (chapter != null && lectures != null)
        scope.launch {
            Data.lecturesRepository.insertBookmarkLectures(
                chapter,
                *lectures.toTypedArray()
            )
            state.value = state.value.copy(bookmarked = true)
        }
}

fun LecturesPort.insertChapterProgress(lecturesCount: Int) {
    val chapter = Data.chaptersRepository.currentChapter
    if (chapter != null)
        scope.launch {
            Data.chaptersRepository.insertChapterProgress(
                Chapter(
                    name = chapter.name,
                    doctorName = chapter.doctorName,
                    imageUrl = chapter.imageUrl,
                    lecturesCount = lecturesCount
                )
            )
        }
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