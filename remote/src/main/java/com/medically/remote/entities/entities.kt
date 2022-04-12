package com.medically.remote.entities

import com.medically.model.Subject
import com.medically.model.Year

data class RemoteYear(
    override val title: String,
    override val subjects: List<Subject>
) : Year()

data class RemoteSubject(override val id: String, override val name: String) : Subject()