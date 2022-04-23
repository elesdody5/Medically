package com.medically.data.repository

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.data.integration.RemoteDataSources
import com.medically.data.remote.ChaptersRemoteDataSource
import com.medically.model.Chapter
import com.medically.model.Result

class ChaptersRepositoryImp(
    private val chaptersRemoteDataSource: ChaptersRemoteDataSource = RemoteDataSources.chaptersRemoteDataSource
) : ChaptersRepositoryPort {
    override suspend fun getChapters(doctorName: String): Result<List<Chapter>> {
        return chaptersRemoteDataSource.getChapters(doctorName)
    }
}