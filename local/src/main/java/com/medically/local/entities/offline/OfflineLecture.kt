package com.medically.local.entities.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    @ColumnInfo(name = "doctor")
    var doctor: String? = null,
)


fun Lecture.toLocalLecture(): OfflineLecture {
    return OfflineLecture(name = name, url = url, chapter = chapterName, doctor)
}


fun Array<out Lecture>.toLocalLecture() =
    map { it.toLocalLecture() }.toTypedArray()

fun List<OfflineLecture>.toLecture(): List<Lecture> {
    return map { Lecture(it.name ?: "", it.url, it.chapter ?: "", it.doctor ?: "") }
}

