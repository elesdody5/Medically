package com.medically.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.domain.model.Lecture


@Entity(tableName = "lecturesEntry")
data class LocalLecture(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val chapterId: Long?,
    @ColumnInfo(name = "lecture_name")
    val name: String,
    @ColumnInfo(name = "lecture_number")
    val number: Int,
    @ColumnInfo(name = "lecture_url")
    val url: String,
    @ColumnInfo(name = "lecture_size")
    val size: Long,
)

fun LocalLecture.toUiLecture(): Lecture {
    return Lecture(number, name, url, size)
}