package com.medically.local.datasource

import com.medically.data.local.ChaptersLocalDataSource
import com.medically.local.db.OfflineDao
import com.medically.local.entities.offline.toChapters
import com.medically.model.Chapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChaptersLocalDataSourceImp(private val offlineDao: OfflineDao) : ChaptersLocalDataSource {

    override suspend fun getOfflineChapters(): Flow<List<Chapter>> {
        return offlineDao.getChapters().map { it.toChapters() }
    }
}