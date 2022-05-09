package com.medically.presentation.audio_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.player.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.net.URLConnection

class AudioViewModel :
    ViewModel(), PlayerPort {
    override val scope = viewModelScope
    override val state = MutableStateFlow(PlayerPortState())
    override val mediaPosition = MutableStateFlow(0L)
    override var updatePosition: Boolean = true
    override val playbackStateCollector = FlowCollector(::onPlaybackStateChanged)
    override val mediaStateCollector = FlowCollector(::onMetaDataChanged)
    override val bindCollector = bindCollector()
    override val bindChapter = bindChapter()
    override val bindDoctor = bindDoctor()


    override fun onCleared() {
        super.onCleared()
        // Stop updating the position
        updatePosition = false
    }

    fun getLectureSize() {
        viewModelScope.launch {
            state isLoading true
            val size = getCurrentLectureSize()
            state.value = state.value.copy(downloadAlertVisibility = true, lectureSize = size)
            state isLoading false
        }
    }

    private suspend fun getCurrentLectureSize(): Long {
        kotlin.runCatching {
            return withContext(Dispatchers.IO) {
                val lectureUrl = state.value.mediaMetadata?.url
                val myUrl = URL(lectureUrl)
                val urlConnection: URLConnection = myUrl.openConnection()
                urlConnection.connect()
                urlConnection.contentLengthLong
            }
        }
        return 0
    }
}
