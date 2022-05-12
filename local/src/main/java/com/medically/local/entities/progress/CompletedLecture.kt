package com.medically.local.entities.progress

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.model.Lecture

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

fun Lecture.toCompleted(): LectureProgressEntity {
    return LectureProgressEntity(
        name = name,
        url = url,
        chapter = chapterName,
        isCompleted = isCompleted
    )
}


fun Array<out Lecture>.toLectureProgress(): Array<LectureProgressEntity> =
    map { it.toCompleted() }.toTypedArray()

fun List<LectureProgressEntity>.toLectureProgress(): List<Lecture> {
    return map {
        Lecture(
            name = it.name,
            url = it.url,
            chapterName = it.chapter,
            isCompleted = it.isCompleted
        )
    }
}

fun List<Lecture>.toLectureProgressEntity(): List<LectureProgressEntity> {
    return map {
        LectureProgressEntity(
            name = it.name,
            url = it.url,
            chapter = it.chapterName,
            isCompleted = it.isCompleted
        )
    }
}
