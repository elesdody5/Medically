package com.medically.core.progress

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import com.medically.model.ChapterProgress
import kotlinx.coroutines.flow.MutableStateFlow

interface ChaptersProgressPort : PresentationPort<ChaptersProgressPortState> {
    val bindChaptersProgress: BusinessRule
}

data class ChaptersProgressPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val chaptersProgress: Map<String, List<ChapterProgress>> = emptyMap()
) : PresentationPortState()

infix fun MutableStateFlow<ChaptersProgressPortState>.isLoading(isLoading: Boolean) {
    value = value.copy(isLoading = isLoading)
}