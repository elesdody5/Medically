package com.medically.data.repository

import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.data.InMemoryCache
import com.medically.data.integration.RemoteDataSources
import com.medically.data.remote.LecturesRemoteDataSource
import com.medically.model.AudioPlayList
import com.medically.model.Lecture
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

class LecturesRepository(
    private val lecturesRemoteDataSource: LecturesRemoteDataSource = RemoteDataSources.lecturesRemoteDataSource,
) : LecturesRepositoryPort {
    override suspend fun getChapterLectures(): Result<List<Lecture>?> {
        val chapter = InMemoryCache.currentChapter

        chapter?.let {
            return lecturesRemoteDataSource.getChapterLectures(it.name)
        }

        return Result.Error(IllegalArgumentException("Chapter not found"))
    }

    override suspend fun getCurrentPlayList(): Flow<AudioPlayList> {
        return InMemoryCache.currentPlayList
    }

    override suspend fun setCurrentPlayList(audioPlayList: AudioPlayList) {
        InMemoryCache.currentPlayList.value = audioPlayList
        //preferencesManager.setCurrentAudioPlayList(audioPlayList)
    }
}