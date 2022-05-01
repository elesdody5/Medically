package com.medically.core.subject_details

import com.medically.core.persentation.PresentationPortState
import com.medically.model.*

data class SubjectDetailsPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val chapters: List<Chapter> = emptyList(),
    val videos: List<Video> = emptyList(),
    val pdfs: List<Pdf> = emptyList(),
    val subject: Subject? = null,
    val filteredChapters: List<Chapter> = emptyList(),
    val doctors: List<Doctor?> = emptyList()
) : PresentationPortState(isLoading, errorMessage)

