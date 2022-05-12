package com.medically.core.completed

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import com.medically.model.Lecture
import kotlinx.coroutines.flow.MutableStateFlow

interface CompletedLecturesPort : PresentationPort<CompletedLecturesPortState> {
    val bindLectures: BusinessRule
    val bindCurrentChapter: BusinessRule
}

data class CompletedLecturesPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val chapter: Chapter? = null,
    val lectures: List<Lecture> = emptyList()

) : PresentationPortState()

infix fun MutableStateFlow<CompletedLecturesPortState>.isLoading(isLoading: Boolean) {
    value = value.copy(isLoading = isLoading)
}