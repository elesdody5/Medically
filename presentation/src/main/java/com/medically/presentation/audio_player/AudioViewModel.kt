package com.medically.presentation.audio_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.player.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

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
}