package com.medically.core.lectures

import com.medically.model.Lecture
import com.medically.model.Result

interface LecturesRepositoryPort {
    suspend fun getChapterLectures(): Result<List<Lecture>?>
}