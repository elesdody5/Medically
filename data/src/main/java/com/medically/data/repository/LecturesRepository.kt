package com.medically.data.repository

import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.data.InMemoryCache
import com.medically.data.integration.LocalDataSources
import com.medically.data.integration.RemoteDataSources
import com.medically.data.local.LecturesLocalDataSource
import com.medically.data.remote.LecturesRemoteDataSource
import com.medically.model.AudioPlayList
import com.medically.model.Chapter
import com.medically.model.Lecture
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

class LecturesRepository(
    private val lecturesRemoteDataSource: LecturesRemoteDataSource = RemoteDataSources.lecturesRemoteDataSource,
    private val lecturesLocalDataSource: LecturesLocalDataSource = LocalDataSources.lecturesLocalDataSource
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

    override suspend fun insertOfflineLectures(chapter: Chapter, vararg lecture: Lecture) {
        lecturesLocalDataSource.insertLecture(chapter, *lecture)
    }

    override fun getOfflineLectures(chapter: String): Flow<List<Lecture>> {
        return lecturesLocalDataSource.getOfflineLectures(chapter)
    }

    override suspend fun insertBookmarkLectures(chapter: Chapter, vararg lecture: Lecture) {
        lecturesLocalDataSource.insertBookmarkLectures(chapter, *lecture)
    }

    override fun getBookmarkedLectures(chapter: String): Flow<List<Lecture>> {
        return lecturesLocalDataSource.getBookmarkedLectures(chapter)
    }
}