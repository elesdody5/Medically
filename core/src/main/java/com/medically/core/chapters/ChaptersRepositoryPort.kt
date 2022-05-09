package com.medically.core.chapters

import com.medically.model.Chapter
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

interface ChaptersRepositoryPort {
    val currentChapter: Chapter?
    fun setCurrentChapter(chapter: Chapter)
    suspend fun getChapters(doctorName: String): Result<List<Chapter>>
    suspend fun getOfflineChapters(): Flow<List<Chapter>>
}