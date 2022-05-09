package com.medically.presentation.downloaded_lectures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.lectures.LecturesPort
import com.medically.core.lectures.LecturesPortState
import com.medically.core.lectures.bindCurrentChapter
import com.medically.core.lectures.bindOfflineLectures
import kotlinx.coroutines.flow.MutableStateFlow

class DownLoadedLecturesViewModel : ViewModel(), LecturesPort {
    override val state = MutableStateFlow(LecturesPortState())
    override val scope = viewModelScope
    override val bindChapter = bindCurrentChapter()
    override val bindLectures = bindOfflineLectures()
}