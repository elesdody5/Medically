package com.medically.core.lectures

import com.medically.model.AudioPlayList
import com.medically.model.Chapter
import com.medically.model.Lecture
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

interface LecturesRepositoryPort {
    suspend fun getChapterLectures(): Result<List<Lecture>?>
    suspend fun getCurrentPlayList(): Flow<AudioPlayList>
    suspend fun setCurrentPlayList(audioPlayList: AudioPlayList)
    suspend fun insertOfflineLectures(chapter: Chapter, vararg lecture: Lecture)
    fun getOfflineLectures(chapter: Chapter): Flow<List<Lecture>>
    suspend fun insertBookmarkLectures(chapter: Chapter, vararg lecture: Lecture)
    suspend fun removeBookmarkLectures(vararg lecture: Lecture)
    fun getBookmarkedLectures(chapter: Chapter): Flow<List<Lecture>>
    suspend fun completeLecture(chapter: Chapter, lectureProgress: Lecture)
    suspend fun getCompletedLectures(chapter: Chapter): Flow<List<Lecture>>
    suspend fun isLectureBookmarked(url: String): Boolean
    fun setCurrentDownLoadedLectures(lecture: List<Lecture>)
    fun getCurrentDownLoadedLectures(): List<Lecture>
}