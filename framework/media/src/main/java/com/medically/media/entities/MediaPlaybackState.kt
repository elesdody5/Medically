package com.medically.media.entities

import android.support.v4.media.session.PlaybackStateCompat
import com.medically.media.extensions.currentPlayBackPosition
import com.medically.media.extensions.isPlaying
import com.medically.model.PlaybackState

data class MediaPlaybackState(
    val state: PlaybackStateCompat? = null
) : PlaybackState() {
    override val isPlaying: Boolean
        get() = state?.isPlaying ?: false
    override val position: Long
        get() = state?.currentPlayBackPosition ?: 0L
    override val speed: Float
        get() = state?.playbackSpeed ?: 0f

}
