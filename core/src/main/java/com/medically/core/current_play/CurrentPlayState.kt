package com.medically.core.current_play

import com.medically.core.persentation.PresentationPortState
import com.medically.model.NowPlayingMetadata

data class CurrentPlayState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val currentPlaying: NowPlayingMetadata? = null
) :
    PresentationPortState()
