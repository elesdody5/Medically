package com.medically.core.player

import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.NowPlayingMetadata
import com.medically.model.PlaybackState

data class PlayerPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val mediaMetadata: NowPlayingMetadata? = null,
    val playbackState: PlaybackState? = null,
    val currentChapter: Chapter? = null,
    val currentDoctor: Doctor? = null
) : PresentationPortState()
