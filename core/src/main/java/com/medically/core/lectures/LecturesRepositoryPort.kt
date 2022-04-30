package com.medically.core.lectures

import com.medically.model.AudioPlayList
import com.medically.model.Lecture
import com.medically.model.Result
import kotlinx.coroutines.flow.Flow

interface LecturesRepositoryPort {
    suspend fun getChapterLectures(): Result<List<Lecture>?>
    suspend fun getCurrentPlayList(): Flow<AudioPlayList>
    suspend fun setCurrentPlayList(audioPlayList: AudioPlayList)
}