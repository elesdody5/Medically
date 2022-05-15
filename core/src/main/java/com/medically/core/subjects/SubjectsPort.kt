package com.medically.core.subjects

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import com.medically.model.NowPlayingMetadata
import com.medically.model.Subject
import kotlinx.coroutines.flow.MutableStateFlow

interface SubjectsPort : PresentationPort<SubjectsPortState> {
    override val state: MutableStateFlow<SubjectsPortState>
    val bindSubjects: BusinessRule
    val bindCurrentPlay: BusinessRule
}

data class SubjectsPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val subjects: Map<String, List<Subject>> = emptyMap(),
    val filteredSubjects: Map<String, List<Subject>> = emptyMap(),
    val currentPlay: NowPlayingMetadata? = null
) : PresentationPortState(isLoading, errorMessage)

infix fun MutableStateFlow<SubjectsPortState>.hasError(message: String) {
    value = value.copy(isLoading = false, errorMessage = message)
}