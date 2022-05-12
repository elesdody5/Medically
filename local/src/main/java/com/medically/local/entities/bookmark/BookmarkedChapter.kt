package com.medically.local.entities.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.medically.model.Chapter

@Entity(tableName = "bookmark_chapter", primaryKeys = ["name", "doctor"])
data class BookmarkedChapter(
    @ColumnInfo(name = "doctor")
    val doctorName: String,
    val name: String,
    @ColumnInfo(name = "image")
    val imageUrl: String?
)

fun Chapter.toBookmark(): BookmarkedChapter {
    return BookmarkedChapter(doctorName, name, imageUrl)
}

fun List<BookmarkedChapter>.toChapters(): List<Chapter> {
    return map { Chapter(doctorName = it.doctorName, name = it.name, imageUrl = it.imageUrl ?: "") }
}
