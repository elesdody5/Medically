package com.medically.core.lectures

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import com.medically.model.Lecture
import kotlinx.coroutines.flow.MutableStateFlow

interface LecturesPort : PresentationPort<LecturesPortState> {
    override val state: MutableStateFlow<LecturesPortState>
    val bindLectures: BusinessRule
    val bindChapter: BusinessRule
}


data class LecturesPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val bookmarked: Boolean? = null,
    val downloadStart: Boolean? = null,
    val chapter: Chapter? = null,
    val lectures: List<Lecture>? = null

) : PresentationPortState()

infix fun MutableStateFlow<LecturesPortState>.isLoading(isLoading: Boolean) {
    value = value.copy(isLoading = isLoading)
}

