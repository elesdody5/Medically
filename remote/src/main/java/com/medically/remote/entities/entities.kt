package com.medically.remote.entities

import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.Subject
import kotlinx.serialization.Serializable

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
    override val id: String,
    override val name: String,
    override val doctorName: String?,
    override val imageUrl: String?
) : Chapter()