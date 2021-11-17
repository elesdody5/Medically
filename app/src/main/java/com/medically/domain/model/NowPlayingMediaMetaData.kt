package com.medically.domain.model

import android.net.Uri

/**
 * Utility class used to represent the metadata necessary to display the
 * media item currently being played.
 */
data class NowPlayingMetadata(
    val id: String,
    val albumArtUri: Uri? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val duration: String? = null
)