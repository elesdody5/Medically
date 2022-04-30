package com.medically.core.player

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort
import com.medically.model.NowPlayingMetadata
import com.medically.model.PlaybackState
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

interface PlayerPort : PresentationPort<PlayerPortState> {
    val musicServiceConnection: MusicServiceConnectionPort
    val mediaPosition: MutableStateFlow<Long>
    var updatePosition: Boolean
    val playbackStateCollector: FlowCollector<PlaybackState?>
    val mediaStateCollector: FlowCollector<NowPlayingMetadata?>
    val bindCollector: BusinessRule
    val bindChapter: BusinessRule
    val bindDoctor: BusinessRule
}