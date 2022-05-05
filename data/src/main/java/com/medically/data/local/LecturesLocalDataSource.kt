package com.medically.data.local

import com.medically.model.Chapter
import com.medically.model.Lecture

interface LecturesLocalDataSource {
    suspend fun insertLecture(chapter: Chapter, vararg lecture: Lecture)
    suspend fun getLectures(chapter: String): List<Lecture>
}