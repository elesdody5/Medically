package com.medically.media.connection.states

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat

sealed class MediaControllerState {
    data class PlaybackStateChanged(val state: PlaybackStateCompat?) : MediaControllerState()
    data class MetadataChanged(val metadata: MediaMetadataCompat?) : MediaControllerState()
    data class SessionEvent(val event: String?) : MediaControllerState()
    object SessionDestroyed : MediaControllerState()
}
