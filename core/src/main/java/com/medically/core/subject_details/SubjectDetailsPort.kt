package com.medically.core.subject_details

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import kotlinx.coroutines.flow.MutableStateFlow

interface SubjectDetailsPort : PresentationPort<SubjectDetailsPortState> {
    override val state: MutableStateFlow<SubjectDetailsPortState>
    val bindCurrentSubject: BusinessRule
    val bindDoctors: BusinessRule
}