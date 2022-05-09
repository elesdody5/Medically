package com.medically.core.player

import com.medically.core.persentation.PresentationPortState
import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.NowPlayingMetadata
import com.medically.model.PlaybackState
import kotlinx.coroutines.flow.MutableStateFlow

data class PlayerPortState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val mediaMetadata: NowPlayingMetadata? = null,
    val playbackState: PlaybackState? = null,
    val currentChapter: Chapter? = null,
    val currentDoctor: Doctor? = null,
    val downloadAlertVisibility: Boolean = false,
    val lectureSize: Long = 0
) : PresentationPortState()

infix fun MutableStateFlow<PlayerPortState>.isLoading(isLoading: Boolean) {
    value = value.copy(isLoading = isLoading)
}