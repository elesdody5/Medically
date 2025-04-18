package com.medically.core.chapters

import com.medically.model.Chapter
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

interface ChaptersRepositoryPort {
    val currentChapter: Chapter?
    fun setCurrentChapter(chapter: Chapter)
    suspend fun getChapters(doctorName: String): Result<List<Chapter>>
    fun getOfflineChapters(): Flow<List<Chapter>>
    fun getBookmarksChapters(): Flow<List<Chapter>>
    fun getChaptersProgress(): Flow<List<Chapter>>
    suspend fun insertChapterProgress(chapter: Chapter)
    suspend fun removeBookmarkChapter(vararg chapter: Chapter)
    suspend fun removeOfflineChapters(vararg chapter: Chapter)
}