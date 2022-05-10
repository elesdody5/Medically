package com.medically.local.entities.progress

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.model.LectureProgress

@Entity(tableName = "lecture_progress")
data class LectureProgressEntity(
    @PrimaryKey
    @ColumnInfo(name = "lecture_url")
    val url: String,
    @ColumnInfo(name = "lecture_name")
    val name: String,
    @ColumnInfo(name = "chapter")
    val chapter: String,
    @ColumnInfo(name = "completed")
    val isCompleted: Boolean = false,
)

fun LectureProgress.toCompleted(): LectureProgressEntity {
    return LectureProgressEntity(
        name = name,
        url = url,
        chapter = chapterName,
        isCompleted = isCompleted
    )
}


fun Array<out LectureProgress>.toLectureProgress(): Array<LectureProgressEntity> =
    map { it.toCompleted() }.toTypedArray()

fun List<LectureProgressEntity>.toLectureProgress(): List<LectureProgress> {
    return map {
        LectureProgress(
            name = it.name,
            url = it.url,
            chapterName = it.chapter,
            isCompleted = it.isCompleted
        )
    }
}

fun List<LectureProgress>.toLectureProgressEntity(): List<LectureProgressEntity> {
    return map {
        LectureProgressEntity(
            name = it.name,
            url = it.url,
            chapter = it.chapterName,
            isCompleted = it.isCompleted
        )
    }
}
