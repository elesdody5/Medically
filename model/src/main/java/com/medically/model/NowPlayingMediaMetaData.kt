package com.medically.model



/**
 * Utility class used to represent the metadata necessary to display the
 * media item currently being played.
 */
data class NowPlayingMetadata(
    val id: String,
    val albumArtUri: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val duration: String? = null
)