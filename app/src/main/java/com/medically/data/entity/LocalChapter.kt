package com.medically.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.domain.model.Chapter


@Entity(tableName = "chaptersEntry")
data class LocalChapter(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val subjectId: Long?,
    val doctorName: String?,
    val name: String,
    val imageUrl: String,
)

fun LocalChapter.toUiChapter(): Chapter {
    return Chapter(name, imageUrl)
}
