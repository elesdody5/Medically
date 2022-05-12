package com.medically.data.local

import com.medically.model.Chapter
import kotlinx.coroutines.flow.Flow

interface ChaptersLocalDataSource {
    fun getOfflineChapters(): Flow<List<Chapter>>
    fun getBookmarksChapters(): Flow<List<Chapter>>
    fun getChaptersProgress(): Flow<List<Chapter>>
    suspend fun insertChapterProgress(chapterProgress: Chapter)
}