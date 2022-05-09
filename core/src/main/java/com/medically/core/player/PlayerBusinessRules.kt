package com.medically.core.player

import com.medically.core.integration.Data
import com.medically.core.integration.Framework
import com.medically.core.toTimeStamp
import com.medically.model.Lecture
import com.medically.model.NowPlayingMetadata
import com.medically.model.PlaybackState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val POSITION_UPDATE_INTERVAL_MILLIS = 100L

fun PlayerPort.onPlaybackStateChanged(playbackState: PlaybackState?) {
    playbackState?.let {
        state.value = state.value.copy(playbackState = playbackState)
    }

}

fun PlayerPort.onMetaDataChanged(nowPlayingMetadata: NowPlayingMetadata?) {
    nowPlayingMetadata?.let {
        state.value = state.value.copy(mediaMetadata = nowPlayingMetadata)
    }
}

fun PlayerPort.bindCollector() {
    scope.launch {
        Framework.musicServiceConnectionPort.playbackState.collect(playbackStateCollector)
    }

    scope.launch { Framework.musicServiceConnectionPort.nowPlaying.collect(mediaStateCollector) }
    scope.launch { checkPlaybackPosition() }
}


/**
 * Internal function that recursively calls itself every [POSITION_UPDATE_INTERVAL_MILLIS] ms
 * to check the current playback position and updates the corresponding LiveData object when it
 * has changed.
 */
suspend fun PlayerPort.checkPlaybackPosition() {

    withContext(Dispatchers.Default) {
        delay(POSITION_UPDATE_INTERVAL_MILLIS)
        val currPosition = state.value.playbackState?.position
        if (mediaPosition.value != currPosition)
            mediaPosition.value = currPosition ?: 0L
        if (updatePosition) {
            checkPlaybackPosition()
        }
    }
}


fun PlayerPort.bindChapter() {
    val chapter = Data.chaptersRepository.currentChapter
    state.value = state.value.copy(currentChapter = chapter)

}

fun PlayerPort.toggleState() {
    if (state.value.playbackState?.isPlaying == true) {
        Framework.musicServiceConnectionPort.pause()
    } else {
        Framework.musicServiceConnectionPort.play()
    }
}

fun PlayerPort.speed(speed: Float) {
    Framework.musicServiceConnectionPort.setSpeed(speed)
}

fun PlayerPort.skipForward() {
    val currentPosition = state.value.playbackState?.position ?: 0
    val seconds = 10.toTimeStamp()
    Framework.musicServiceConnectionPort.seekTo(currentPosition + seconds)
}

fun PlayerPort.skipBackward() {
    val currentPosition = state.value.playbackState?.position ?: 0
    val seconds = 10.toTimeStamp()
    Framework.musicServiceConnectionPort.seekTo(currentPosition - seconds)
}

fun PlayerPort.seekTo(position: Long) {
    Framework.musicServiceConnectionPort.seekTo(position)
}

fun PlayerPort.downLoadAudio() {
    val nowPlaying = state.value.mediaMetadata
    val chapter = state.value.currentChapter
    val downloader = Framework.downLoaderManager
    val lecture = Lecture(nowPlaying?.title ?: "", nowPlaying?.url ?: "", chapter?.name ?: "")
    chapter?.let { downloader.downLoad(lecture, it) }
}



