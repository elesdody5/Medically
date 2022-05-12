/*
 * Copyright 2018 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.medically.media.connection

import android.app.Application
import android.content.ComponentName
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.medically.core.player.MusicServiceConnectionPort
import com.medically.media.connection.states.MediaBrowserState
import com.medically.media.connection.states.MediaControllerState.*
import com.medically.media.entities.MediaPlaybackState
import com.medically.media.extensions.*
import com.medically.media.service.NETWORK_FAILURE
import com.medically.model.NowPlayingMetadata
import com.medically.model.PlaybackState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * Class that manages a connection to a [MediaBrowserServiceCompat] instance, typically a
 * [MusicService] or one of its subclasses.
 *
 * Typically it's best to construct/inject dependencies either using DI or,
 * using [CoreIntegration] in the core module. There are a few difficulties for that here:
 * - [MediaBrowserCompat] is a final class, so mocking it directly is difficult.
 * - A [MediaBrowserConnectionCallback] is a parameter into the construction of
 *   a [MediaBrowserCompat], and provides callbacks to this class.
 * - [MediaBrowserCompat.ConnectionCallback.onConnected] is the best place to construct
 *   a [MediaControllerCompat] that will be used to control the [MediaSessionCompat].
 *
 *  Because of these reasons, rather than constructing additional classes, this is treated as
 *  a black box (which is why there's very little logic here).
 *
 *  This is also why the parameters to construct a [MusicServiceConnection] are simple
 *  parameters, rather than private properties. They're only required to build the
 *  [MediaBrowserConnectionCallback] and [MediaBrowserCompat] objects.
 */
class MusicServiceConnection(
    private val application: Application,
    serviceComponent: ComponentName
) : MusicServiceConnectionPort {
    override val isConnected = MutableStateFlow(false)
    override val networkFailure = MutableStateFlow(false)

    override val rootMediaId: String get() = mediaBrowser.root

    override val playbackState: MutableStateFlow<PlaybackState?> =
        MutableStateFlow(null)

    override val nowPlaying = MutableStateFlow<NowPlayingMetadata?>(null)

    private val transportControls: MediaControllerCompat.TransportControls
        get() = mediaController.transportControls

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback()

    private val mediaBrowser = MediaBrowserCompat(
        application,
        serviceComponent,
        mediaBrowserConnectionCallback,
        null
    ).apply { connect() }

    private lateinit var mediaController: MediaControllerCompat
    private val mediaControllerCallback = MediaControllerCallback()
    private val connectionScope = CoroutineScope(Dispatchers.Main)

    init {
        connectionScope.launch { observeOnMediaBrowserConnection() }
        connectionScope.launch { observeOnMediaController() }
    }

    override fun play() {
        transportControls.play()
    }

    override fun pause() {
        transportControls.pause()
    }

    override fun seekTo(position: Long) {
        transportControls.seekTo(position)
    }

    //TODO speed not changed need to check
    override fun setSpeed(speed: Float) {
        transportControls.setPlaybackSpeed(speed)
    }

    fun subscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.subscribe(parentId, callback)
    }

    fun unsubscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.unsubscribe(parentId, callback)
    }

    fun sendCommand(command: String, parameters: Bundle?) =
        sendCommand(command, parameters) { _, _ -> }

    private fun sendCommand(
        command: String,
        parameters: Bundle?,
        resultCallback: ((Int, Bundle?) -> Unit)
    ) = if (mediaBrowser.isConnected) {
        mediaController.sendCommand(command, parameters, object : ResultReceiver(Handler()) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                resultCallback(resultCode, resultData)
            }
        })
        true
    } else {
        false
    }

    private suspend fun observeOnMediaBrowserConnection() {
        mediaBrowserConnectionCallback.mediaBrowserConnectionState.collect {
            when (it) {
                is MediaBrowserState.IsConnected -> onConnected()
                is MediaBrowserState.OnConnectionFailed -> isConnected.value = false
                is MediaBrowserState.OnConnectionSuspended -> isConnected.value = false
            }
        }
    }

    private suspend fun observeOnMediaController() {
        mediaControllerCallback.mediaControllerState.collect {
            when (it) {
                is PlaybackStateChanged -> playbackState.value = MediaPlaybackState(it.state)
                is MetadataChanged -> metaDataChanged(it.metadata)
                is SessionEvent -> onSessionEvent(it.event)
                is SessionDestroyed -> isConnected.value = false
            }
        }
    }

    private fun onConnected() {
        // Get a MediaController for the MediaSession.
        mediaController = MediaControllerCompat(application, mediaBrowser.sessionToken).apply {
            registerCallback(mediaControllerCallback)
        }
        isConnected.value = true
    }

    private fun metaDataChanged(metadata: MediaMetadataCompat?) {
        metadata?.let {
            if (it.id != null) {
                nowPlaying.value = NowPlayingMetadata(
                    it.id!!,
                    it.trackNumber,
                    it.albumArtUri.toString(),
                    it.mediaUri.toString(),
                    it.title?.trim(),
                    it.displaySubtitle?.trim(),
                    it.duration
                )
            }
        }
    }

    private fun onSessionEvent(event: String?) {
        when (event) {
            NETWORK_FAILURE -> networkFailure.value = true
        }
    }

    companion object {
        // For Singleton instantiation.
        @Volatile
        private var instance: MusicServiceConnection? = null

        fun getInstance(application: Application, serviceComponent: ComponentName) =
            instance ?: synchronized(this) {
                instance ?: MusicServiceConnection(application, serviceComponent)
                    .also { instance = it }
            }
    }

}

