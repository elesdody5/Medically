package com.medically.model

open class Subject(
    open val id: String = "",
    open val name: String = "",
    open val yearName: String = "",
    open val icon: String? = null
)

open class Chapter(
    open val id: String? = null,
    open val doctorName: String? = null,
    open val name: String = "",
    open val imageUrl: String? = null,
)

open class Doctor(
    open val subjectId: String? = null,
    open val name: String? = null,
)

open class Pdf(
    open val name: String? = null,
    open val url: String? = null,
)

open class Video(
    open val name: String? = null,
    open val url: String? = null,
)

open class Lecture(
    open val name: String? = null,
    open val url: String? = null,
    open val chapterName: String? = null
)

open class AudioPlayList(
    open val lectures: List<Lecture>? = null,
    open val currentPlayingPosition: Int? = null,
    open val chapter: Chapter? = null,
    open val doctor: Doctor? = null,
    open val subject: Subject? = null
) 