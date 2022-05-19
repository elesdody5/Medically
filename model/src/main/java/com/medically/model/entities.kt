package com.medically.model

open class Subject(
    open val id: String = "",
    open val name: String = "",
    open val yearName: String = "",
    open val icon: String? = null
)

open class Chapter(
    open val doctorName: String = "",
    open val name: String = "",
    open val imageUrl: String = "",
    open var progress: Int = 0,
    open var lecturesCount: Int = 0
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
    open val name: String,
    open val url: String,
    open val chapterName: String,
    open val doctor: String,
    open var isCompleted: Boolean = false
)

open class AudioPlayList constructor(
    open val lectures: List<Lecture>? = null,
    open val currentPlayingPosition: Int? = null,
    open val chapter: Chapter? = null,
    open val doctorName: String? = null,
    open val subjectTitle: String? = null
) 