package com.medically.local.entities.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.model.Lecture

@Entity(tableName = "lecturesEntry")
class OfflineLecture(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lecture_id")
    var id: Int = 0,
    @ColumnInfo(name = "lecture_name")
    var name: String? = null,
    @ColumnInfo(name = "lecture_url")
    var url: String? = null,
    @ColumnInfo(name = "chapter")
    var chapter: String? = null,
)


fun Lecture.toLocalLecture(): OfflineLecture {
    return OfflineLecture(name = name, url = url, chapter = chapterName)
}


fun Array<out Lecture>.toLocalLecture() =
    map { it.toLocalLecture() }.toTypedArray()

fun List<OfflineLecture>.toLecture(): List<Lecture> {
    return map { Lecture(it.name ?: "", it.url ?: "", it.chapter ?: "") }
}