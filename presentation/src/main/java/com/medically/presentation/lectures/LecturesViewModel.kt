package com.medically.presentation.lectures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.lectures.*
import kotlinx.coroutines.flow.MutableStateFlow

class LecturesViewModel : ViewModel(), LecturesPort {
    override val state = MutableStateFlow(LecturesPortState())
    override val scope = viewModelScope
    override val bindLectures = bindLectures()
    override val bindChapter = bindCurrentChapter()
    override val bindDoctor = bindCurrentDoctor()
}