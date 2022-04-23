package com.medically.presentation.subject_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var currentPage by mutableStateOf(0)
        private set

    fun onTabSelected(index: Int) {
        currentPage = index
    }

}