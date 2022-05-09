package com.medically.data.local

import com.medically.model.Chapter
import com.medically.model.Lecture
import kotlinx.coroutines.flow.Flow

interface LecturesLocalDataSource {
    suspend fun insertLecture(chapter: Chapter, vararg lecture: Lecture)
    fun getLectures(chapter: String): Flow<List<Lecture>>
}