package com.medically.data.repository

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.data.InMemoryCache
import com.medically.data.integration.LocalDataSources
import com.medically.data.integration.RemoteDataSources
import com.medically.data.local.ChaptersLocalDataSource
import com.medically.data.remote.ChaptersRemoteDataSource
import com.medically.model.Chapter
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

class ChaptersRepository(
    private val chaptersRemoteDatasource: ChaptersRemoteDataSource = RemoteDataSources.chaptersRemoteDataSource,
    private val chaptersLocalDatasource: ChaptersLocalDataSource = LocalDataSources.chaptersLocalDatasource
) : ChaptersRepositoryPort {

    override val currentChapter: Chapter?
        get() = InMemoryCache.currentChapter


    override fun setCurrentChapter(chapter: Chapter) {
        InMemoryCache.currentChapter = chapter
    }

    override suspend fun getChapters(doctorName: String): Result<List<Chapter>> {
        return chaptersRemoteDatasource.getChapters(doctorName)
    }

    override fun getOfflineChapters(): Flow<List<Chapter>> {
        return chaptersLocalDatasource.getOfflineChapters()
    }

    override fun getBookmarksChapters(): Flow<List<Chapter>> {
        return chaptersLocalDatasource.getBookmarksChapters()
    }
}