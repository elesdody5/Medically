package com.medically.presentation.chapters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.chapters.ChaptersPort
import com.medically.core.chapters.ChaptersPortState
import com.medically.core.chapters.bindCurrentSubject
import com.medically.core.chapters.bindDoctors
import kotlinx.coroutines.flow.MutableStateFlow

class ChaptersViewModel : ChaptersPort,
    ViewModel() {
    override val state = MutableStateFlow(ChaptersPortState())
    override val scope = viewModelScope
    override val bindDoctors = bindDoctors()
    override val bindChaptersSubject = bindCurrentSubject()
}

