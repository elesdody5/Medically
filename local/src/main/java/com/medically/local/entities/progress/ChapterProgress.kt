package com.medically.local.entities.progress

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.medically.model.ChapterProgress

@Entity(tableName = "chapter_progress", primaryKeys = ["name", "doctor"])
data class ChapterProgressEntity(
    @ColumnInfo(name = "doctor")
    val doctorName: String,
    val name: String,
    @ColumnInfo(name = "image")
    var imageUrl: String,
    @ColumnInfo(name = "progress")
    val progress: Int,
    @ColumnInfo(name = "lectures_count")
    val lecturesCount: Int
)

data class ChapterProgressQuery(
    @ColumnInfo(name = "progress")
    val progress: Int,
    val name: String,
    @ColumnInfo(name = "doctor")
    val doctorName: String
)

fun ChapterProgress.toChapterProgressEntity(): ChapterProgressEntity {
    return ChapterProgressEntity(
        name = name,
        doctorName = doctorName,
        imageUrl = imageUrl,
        progress = progress,
        lecturesCount = lecturesCount
    )
}

fun List<ChapterProgressEntity>.toChapterProgress(): List<ChapterProgress> {
    return map {
        ChapterProgress(
            doctorName = it.doctorName,
            name = it.name,
            imageUrl = it.imageUrl,
            progress = it.progress,
            lecturesCount = it.lecturesCount
        )
    }
}

