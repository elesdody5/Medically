package com.medically.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Subject(
    val name: String?,
    val doctors: List<String>?
) : Parcelable

@Parcelize
data class Doctor(val name: String?) : Parcelable

@Parcelize
data class Chapter(val name: String?, val imageUrl: String?) : Parcelable

data class Pdf(val name: String?, val url: String?)

data class Video(val name: String?, val url: String?)

@Parcelize
data class Lecture(val number: Int?, val name: String?, val url: String?, val size: Long?) :
    Parcelable

@Parcelize
data class AudioPlayList(
    val lecture: List<Lecture>,
    val lecturePosition: Int,
    val chapter: Chapter,
    val doctor: Doctor,
    val subject: Subject
) :
    Parcelable