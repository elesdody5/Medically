package com.medically.presentation.downloaded_chapters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.offline.chapters.OfflineChaptersPort
import com.medically.core.offline.chapters.OfflineChaptersState
import com.medically.core.offline.chapters.bindOfflineChapters
import kotlinx.coroutines.flow.MutableStateFlow

class DownloadedChaptersViewModel : ViewModel(), OfflineChaptersPort {
    override val scope = viewModelScope
    override val state = MutableStateFlow(OfflineChaptersState())
    override val bindOfflineChapters = bindOfflineChapters()
}