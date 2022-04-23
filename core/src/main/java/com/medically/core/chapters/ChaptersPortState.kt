package com.medically.core.chapters

import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.Subject

data class ChaptersPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val chapters: List<Chapter> = emptyList(),
    val subject: Subject? = null,
    val filteredChapters: List<Chapter> = emptyList(),
    val doctors: List<Doctor?> = emptyList()
) : PresentationPortState(isLoading, errorMessage)

