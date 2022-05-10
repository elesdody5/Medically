package com.medically.core.chapters

import com.medically.model.Chapter
import com.medically.model.ChapterProgress
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

interface ChaptersRepositoryPort {
    val currentChapter: Chapter?
    fun setCurrentChapter(chapter: Chapter)
    suspend fun getChapters(doctorName: String): Result<List<Chapter>>
    fun getOfflineChapters(): Flow<List<Chapter>>
    fun getBookmarksChapters(): Flow<List<Chapter>>
    fun getChaptersProgress(): Flow<List<ChapterProgress>>
    suspend fun insertChapterProgress(
        chapterProgress: ChapterProgress,
    )
}