package com.medically.media.service

import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.medically.media.service.state.PlayerState
import com.medically.media.service.state.PlayerState.IsReady
import com.medically.media.service.state.PlayerState.NotReady
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Listen for events from ExoPlayer.
 */
class PlayerEventListener : Player.Listener {
    private val _playerState = MutableStateFlow<PlayerState?>(null)
    val playerState: Flow<PlayerState?>
        get() = _playerState

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING, Player.STATE_READY -> {
                _playerState.value = IsReady(playWhenReady)
            }
            else -> {
                _playerState.value = NotReady
            }
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        val message = error.message
        _playerState.value = PlayerState.Error(message)
    }
}