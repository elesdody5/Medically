package com.medically.media.connection

import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import com.medically.media.connection.states.MediaBrowserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MediaBrowserConnectionCallback(private val context: Context) :
    MediaBrowserCompat.ConnectionCallback() {

    private val _mediaBrowserConnectionStates = MutableStateFlow<MediaBrowserState?>(null)

    val mediaBrowserConnectionState: Flow<MediaBrowserState?>
        get() = _mediaBrowserConnectionStates

    /**
     * Invoked after [MediaBrowserCompat.connect] when the request has successfully
     * completed.
     */
    override fun onConnected() {
        _mediaBrowserConnectionStates.value = MediaBrowserState.IsConnected
    }

    /**
     * Invoked when the client is disconnected from the media browser.
     */
    override fun onConnectionSuspended() {
        _mediaBrowserConnectionStates.value = MediaBrowserState.OnConnectionSuspended
    }

    /**
     * Invoked when the connection to the media browser failed.
     */
    override fun onConnectionFailed() {
        _mediaBrowserConnectionStates.value = MediaBrowserState.OnConnectionFailed
    }
}