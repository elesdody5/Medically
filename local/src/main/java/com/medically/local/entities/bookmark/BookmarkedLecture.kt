package com.medically.local.entities.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.model.Lecture

@Entity(tableName = "bookmark_lecture")
data class BookmarkedLecture(
    @ColumnInfo(name = "lecture_name")
    val name: String? = null,
    @ColumnInfo(name = "lecture_url")
    @PrimaryKey
    val url: String,
    @ColumnInfo(name = "chapter")
    val chapter: String? = null,
)

fun Lecture.toBookmarkLecture(): BookmarkedLecture {
    return BookmarkedLecture(name = name, url = url, chapter = chapterName)
}


fun Array<out Lecture>.toBookmarkedLecture(): Array<BookmarkedLecture> =
    map { it.toBookmarkLecture() }.toTypedArray()

fun List<BookmarkedLecture>.toLecture(): List<Lecture> {
    return map { Lecture(it.name ?: "", it.url, it.chapter ?: "") }
}