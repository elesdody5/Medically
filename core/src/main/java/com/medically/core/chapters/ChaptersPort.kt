package com.medically.core.chapters

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import kotlinx.coroutines.flow.MutableStateFlow

interface ChaptersPort : PresentationPort<ChaptersPortState> {
    override val state: MutableStateFlow<ChaptersPortState>
    val bindDoctors: BusinessRule
    val bindChaptersSubject: BusinessRule
}