package com.medically.core.player


import com.medically.model.NowPlayingMetadata
import com.medically.model.PlaybackState
import kotlinx.coroutines.flow.StateFlow

interface MusicServiceConnectionPort {
    val isConnected: StateFlow<Boolean>
    val networkFailure: StateFlow<Boolean>
    val rootMediaId: String
    val playbackState: StateFlow<PlaybackState?>
    val nowPlaying: StateFlow<NowPlayingMetadata?>
    fun play()
    fun pause()
    fun seekTo(position: Long)
    fun setSpeed(speed: Float)
}