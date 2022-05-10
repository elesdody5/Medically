package com.medically.core.lectures

import com.medically.model.*
import kotlinx.coroutines.flow.Flow

interface LecturesRepositoryPort {
    suspend fun getChapterLectures(): Result<List<Lecture>?>
    suspend fun getCurrentPlayList(): Flow<AudioPlayList>
    suspend fun setCurrentPlayList(audioPlayList: AudioPlayList)
    suspend fun insertOfflineLectures(chapter: Chapter, vararg lecture: Lecture)
    fun getOfflineLectures(chapter: String): Flow<List<Lecture>>
    suspend fun insertBookmarkLectures(chapter: Chapter, vararg lecture: Lecture)
    fun getBookmarkedLectures(chapter: String): Flow<List<Lecture>>
    suspend fun completeLecture(chapter: Chapter, lectureProgress: LectureProgress)
}