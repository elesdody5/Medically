package com.medically.presentation.chapters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.chapters.ChaptersPort
import com.medically.core.chapters.ChaptersPortState
import kotlinx.coroutines.flow.MutableStateFlow

class ChaptersViewModel : ViewModel(), ChaptersPort {
    override val scope = viewModelScope
    override val state = MutableStateFlow(ChaptersPortState())
}