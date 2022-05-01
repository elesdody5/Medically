package com.medically.media.connection

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat
import com.medically.media.connection.states.MediaControllerState
import com.medically.media.connection.states.MediaControllerState.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MediaControllerCallback : MediaControllerCompat.Callback() {
    private val _mediaControllerState = MutableStateFlow<MediaControllerState?>(null)

    val mediaControllerState: Flow<MediaControllerState?>
        get() = _mediaControllerState


    override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
        _mediaControllerState.value = PlaybackStateChanged(state)
    }

    override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
        // When ExoPlayer stops we will receive a callback with "empty" metadata. This is a
        // metadata object which has been instantiated with default values. The default value
        // for media ID is null so we assume that if this value is null we are not playing
        // anything.
        _mediaControllerState.value = MetadataChanged(metadata)
    }

    override fun onQueueChanged(queue: MutableList<MediaSessionCompat.QueueItem>?) {
    }

    override fun onSessionEvent(event: String?, extras: Bundle?) {
        super.onSessionEvent(event, extras)
        _mediaControllerState.value = SessionEvent(event)
    }

    /**
     * Normally if a [MediaBrowserServiceCompat] drops its connection the callback comes via
     * [MediaControllerCompat.Callback] (here). But since other connection status events
     * are sent to [MediaBrowserCompat.ConnectionCallback], we catch the disconnect here and
     * send it on to the other callback.
     */
    override fun onSessionDestroyed() {
        _mediaControllerState.value = SessionDestroyed
    }
}
