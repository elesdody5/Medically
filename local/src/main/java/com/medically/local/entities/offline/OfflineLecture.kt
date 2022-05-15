package com.medically.local.entities.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.model.Chapter
import com.medically.model.Lecture

@Entity(tableName = "lecturesEntry")
class OfflineLecture(
    @ColumnInfo(name = "lecture_name")
    var name: String? = null,
    @ColumnInfo(name = "lecture_url")
    @PrimaryKey
    var url: String,
    @ColumnInfo(name = "chapter")
    var chapter: String? = null,
)


fun Lecture.toLocalLecture(): OfflineLecture {
    return OfflineLecture(name = name, url = url, chapter = chapterName)
}


fun Array<out Lecture>.toLocalLecture() =
    map { it.toLocalLecture() }.toTypedArray()

fun List<OfflineLecture>.toLecture(): List<Lecture> {
    return map { Lecture(it.name ?: "", it.url, it.chapter ?: "") }
}

fun List<OfflineChapter>.toChapters(): List<Chapter> {
    return map { Chapter(it.doctorName, it.name, it.imageUrl ?: "") }
}