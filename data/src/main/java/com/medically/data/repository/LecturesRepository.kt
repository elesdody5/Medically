package com.medically.data.repository

import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.data.InMemoryCache
import com.medically.data.integration.PreferencesDataSources
import com.medically.data.integration.RemoteDataSources
import com.medically.data.preferences.PreferencesManager
import com.medically.data.remote.LecturesRemoteDataSource
import com.medically.model.AudioPlayList
import com.medically.model.Lecture
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

class LecturesRepository(
    private val lecturesRemoteDataSource: LecturesRemoteDataSource = RemoteDataSources.lecturesRemoteDataSource,
    private val preferencesManager: PreferencesManager = PreferencesDataSources.preferencesManager
) : LecturesRepositoryPort {
    override suspend fun getChapterLectures(): Result<List<Lecture>?> {
        val chapter = InMemoryCache.currentChapter

        chapter?.let {
            return lecturesRemoteDataSource.getChapterLectures(it.name)
        }

        return Result.Error(IllegalArgumentException("Chapter not found"))
    }

    override suspend fun getCurrentPlayList(): Flow<AudioPlayList> {
        return preferencesManager.currentAudioPlayList
    }

    override suspend fun setCurrentPlayList(audioPlayList: AudioPlayList) {
        preferencesManager.setCurrentAudioPlayList(audioPlayList)
    }
}