package com.medically.data.remote

import com.medically.model.Lecture
import com.medically.model.Result

interface LecturesRemoteDataSource {
    suspend fun getChapterLectures(chapter: String): Result<List<Lecture>>
}