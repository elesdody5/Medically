package com.medically.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subjectEntry")
data class LocalSubject(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val year: String,
    val name: String,
    val doctors: List<String>,
)

