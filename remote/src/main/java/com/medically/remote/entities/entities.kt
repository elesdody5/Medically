package com.medically.remote.entities

import com.medically.model.*

data class RemoteSubject(
    override val id: String,
    override val name: String,
    override val yearName: String,
    override val icon: String? = null
) : Subject()

data class RemoteDoctor(
    override val subjectId: String,
    override val name: String,
) : Doctor()

data class RemoteChapter(
    override val name: String,
    override val doctorName: String,
    override val imageUrl: String
) : Chapter(doctorName, name, imageUrl)

data class RemoteVideo(
    override val name: String?,
    override val url: String?
) : Video()

data class RemotePdf(
    override val name: String?,
    override val url: String?
) : Pdf()

data class RemoteLecture(
    override val name: String,
    override val url: String,
    override val chapterName: String,
    override val doctor: String
) : Lecture(name, url, chapterName, doctor)