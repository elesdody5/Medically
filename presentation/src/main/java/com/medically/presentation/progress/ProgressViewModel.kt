package com.medically.presentation.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.progress.ChaptersProgressPort
import com.medically.core.progress.ChaptersProgressPortState
import com.medically.core.progress.bindChaptersProgress
import kotlinx.coroutines.flow.MutableStateFlow

class ProgressViewModel : ViewModel(), ChaptersProgressPort {
    override val scope = viewModelScope
    override val state = MutableStateFlow(ChaptersProgressPortState())
    override val bindChaptersProgress = bindChaptersProgress()
}