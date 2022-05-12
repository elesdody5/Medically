package com.medically.presentation.completed_lectures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.completed.CompletedLecturesPort
import com.medically.core.completed.CompletedLecturesPortState
import com.medically.core.completed.bindCompletedLectures
import com.medically.core.completed.bindCurrentChapter
import kotlinx.coroutines.flow.MutableStateFlow

class CompletedLecturesViewModel : ViewModel(), CompletedLecturesPort {
    override val scope = viewModelScope
    override val state = MutableStateFlow(CompletedLecturesPortState())
    override val bindLectures = bindCompletedLectures()
    override val bindCurrentChapter = bindCurrentChapter()
}