package com.medically.data.local

import com.medically.model.Chapter
import com.medically.model.Lecture
import kotlinx.coroutines.flow.Flow

interface LecturesLocalDataSource {
    suspend fun insertLecture(chapter: Chapter, vararg lecture: Lecture)
    fun getOfflineLectures(chapter: String): Flow<List<Lecture>>
    suspend fun insertBookmarkLectures(chapter: Chapter, vararg lecture: Lecture)
    fun getBookmarkedLectures(chapter: String): Flow<List<Lecture>>
    suspend fun completeLecture(chapter: Chapter, lecture: Lecture)
    suspend fun getCompletedLectures(chapter: Chapter): Flow<List<Lecture>>
    suspend fun isLectureBookmarked(url: String): Boolean
    suspend fun removeBookmark(vararg lecture: Lecture)
}