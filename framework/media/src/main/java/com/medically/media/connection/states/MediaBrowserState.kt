package com.medically.media.connection.states

sealed class MediaBrowserState {
    object IsConnected : MediaBrowserState()
    object OnConnectionSuspended : MediaBrowserState()
    object OnConnectionFailed : MediaBrowserState()
}
