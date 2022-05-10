package com.medically.model

data class ChapterProgress(
    val name: String,
    val doctorName: String,
    val imageUrl: String,
    val progress: Int = 0,
    val lecturesCount: Int
)

data class LectureProgress(
    val name: String,
    val chapterName: String,
    val url: String,
    val isCompleted: Boolean = false
)