package com.medically.local.entities.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.medically.model.Chapter

@Entity(tableName = "chaptersEntry", primaryKeys = ["name", "doctor"])
data class OfflineChapter(
    @ColumnInfo(name = "doctor")
    var doctorName: String,
    var name: String,
    @ColumnInfo(name = "image")
    var imageUrl: String?
)

fun Chapter.toOfflineChapter(): OfflineChapter {
    return OfflineChapter(doctorName, name, imageUrl)
}

fun List<OfflineChapter>.toChapters(): List<Chapter> {
    return map { Chapter(it.doctorName, it.name, it.imageUrl ?: "") }
}

fun Array<out Chapter>.toOfflineChapters() =
    map { it.toOfflineChapter() }.toTypedArray()