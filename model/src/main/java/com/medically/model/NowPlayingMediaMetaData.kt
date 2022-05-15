package com.medically.model


/**
 * Utility class used to represent the metadata necessary to display the
 * media item currently being played.
 */
data class NowPlayingMetadata(
    val id: String,
    val number: Long = 0,
    val albumArtUri: String,
    val url: String,
    val title: String?,
    val subtitle: String?,
    val duration: Long = 0
)

open class PlaybackState {
    open val isPlaying: Boolean = false
    open val position: Long = 0L
    open val speed: Float = 0f
}