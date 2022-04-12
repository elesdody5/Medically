package com.medically.audioplayer

import android.app.Application
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.medically.domain.model.NowPlayingMetadata
import com.medically.extensions.*
import com.medically.utils.timestampToMSS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel
@Inject constructor(
    private val app: Application,
    private val musicServiceConnection: MusicServiceConnection
) : AndroidViewModel(app) {
    var mediaMetadata by mutableStateOf<NowPlayingMetadata?>(null)
        private set
    var mediaPosition by mutableStateOf(0L)
        private set
    var mediaButtonRes by mutableStateOf(Icons.Filled.PlayArrow)
        private set

    private var updatePosition = true

    private var playbackState: PlaybackStateCompat = EMPTY_PLAYBACK_STATE

    private val playbackStateCollector =
        FlowCollector<PlaybackStateCompat> { value ->
            playbackState = value
            val metadata = musicServiceConnection.nowPlaying.value
            updateState(playbackState, metadata)
        }
    private val mediaMetadataCollector =
        FlowCollector<MediaMetadataCompat> { value -> updateState(playbackState, value) }

    init {
        observeConnection()
    }

    private fun observeConnection() {
        viewModelScope.launch {
            launch { musicServiceConnection.playbackState.collect(playbackStateCollector) }
            launch { musicServiceConnection.nowPlaying.collect(mediaMetadataCollector) }
            launch { checkPlaybackPosition() }
        }
    }

    private fun updateState(
        playbackState: PlaybackStateCompat,
        mediaMetadata: MediaMetadataCompat
    ) {
        // Only update media item once we have duration available
        if (mediaMetadata.duration != 0L && mediaMetadata.id != null) {
            val nowPlayingMetadata = NowPlayingMetadata(
                mediaMetadata.id!!,
                mediaMetadata.albumArtUri,
                mediaMetadata.title?.trim(),
                mediaMetadata.displaySubtitle?.trim(),
                timestampToMSS(app.applicationContext, mediaMetadata.duration)
            )
            this.mediaMetadata = nowPlayingMetadata
        }

        // Update the media button resource ID
        mediaButtonRes =
            when (playbackState.isPlaying) {
                true -> Icons.Filled.Pause
                else -> Icons.Filled.PlayArrow
            }
    }

    /**
     * Internal function that recursively calls itself every [POSITION_UPDATE_INTERVAL_MILLIS] ms
     * to check the current playback position and updates the corresponding LiveData object when it
     * has changed.
     */
    private suspend fun checkPlaybackPosition() {

        withContext(Dispatchers.Default) {
            delay(POSITION_UPDATE_INTERVAL_MILLIS)
            val currPosition = playbackState.currentPlayBackPosition
            if (mediaPosition != currPosition)
                mediaPosition = currPosition
            if (updatePosition) {
                checkPlaybackPosition()
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        // Stop updating the position
        updatePosition = false
    }
}

private const val POSITION_UPDATE_INTERVAL_MILLIS = 100L
