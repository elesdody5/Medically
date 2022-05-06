package com.medically.local.entities.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.medically.model.Chapter

@Entity(tableName = "chaptersEntry")
data class OfflineChapter(
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "doctor")
    var doctorName: String?,
    var name: String,
    @ColumnInfo(name = "image")
    var imageUrl: String?
)

fun Chapter.toOfflineChapter(): OfflineChapter {
    return OfflineChapter(id ?: "", doctorName, name, imageUrl)
}
