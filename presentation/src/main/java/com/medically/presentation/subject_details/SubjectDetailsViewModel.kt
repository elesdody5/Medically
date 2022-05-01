package com.medically.presentation.subject_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.subject_details.SubjectDetailsPort
import com.medically.core.subject_details.SubjectDetailsPortState
import com.medically.core.subject_details.bindCurrentSubject
import com.medically.core.subject_details.bindDoctors
import kotlinx.coroutines.flow.MutableStateFlow

class SubjectDetailsViewModel : SubjectDetailsPort, ViewModel() {
    override val state = MutableStateFlow(SubjectDetailsPortState())
    override val scope = viewModelScope
    override val bindDoctors = bindDoctors()
    override val bindCurrentSubject = bindCurrentSubject()

}