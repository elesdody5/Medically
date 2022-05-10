package com.medically.data.local

import com.medically.model.Chapter
import kotlinx.coroutines.flow.Flow

interface ChaptersLocalDataSource {
    suspend fun getOfflineChapters(): Flow<List<Chapter>>
}