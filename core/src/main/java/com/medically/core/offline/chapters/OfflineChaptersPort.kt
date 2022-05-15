package com.medically.core.offline.chapters

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import kotlinx.coroutines.flow.MutableStateFlow

interface OfflineChaptersPort : PresentationPort<OfflineChaptersState> {
    val bindOfflineChapters: BusinessRule
}

data class OfflineChaptersState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val chapters: Map<String, List<Chapter>> = emptyMap()
) : PresentationPortState()

infix fun MutableStateFlow<OfflineChaptersState>.isLoading(isLoading: Boolean) {
    value = value.copy(isLoading = isLoading)
}