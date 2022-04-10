package com.medically.core.entities

data class Subject(
    val id: Int,
    val name: String,
)

data class Chapter(
    val id: Long?,
    val doctorName: String?,
    val name: String,
    val imageUrl: String,
)

data class Year(
    val title: String,
    val subjects: List<Subject>
)

data class Doctor(val name: String?)