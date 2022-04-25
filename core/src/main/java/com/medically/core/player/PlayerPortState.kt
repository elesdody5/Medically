package com.medically.core.player

import com.medically.core.persentation.PresentationPortState
import com.medically.model.NowPlayingMetadata

data class PlayerPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val mediaMetadata: NowPlayingMetadata? = null,
    val mediaIsPlaying: Boolean = false
) : PresentationPortState()
