package com.medically.presentation.lectures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.lectures.LecturesPort
import com.medically.core.lectures.LecturesPortState
import com.medically.core.lectures.bindCurrentChapter
import com.medically.core.lectures.bindLectures
import kotlinx.coroutines.flow.MutableStateFlow

class LecturesViewModel : ViewModel(), LecturesPort {
    override val state = MutableStateFlow(LecturesPortState())
    override val scope = viewModelScope
    override val bindLectures = bindLectures()
    override val bindChapter = bindCurrentChapter()
}