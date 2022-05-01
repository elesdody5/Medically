package com.medically.data.preferences

import com.medically.model.AudioPlayList
import kotlinx.coroutines.flow.Flow


interface PreferencesManager {
    val currentAudioPlayList: Flow<AudioPlayList>
    suspend fun setCurrentAudioPlayList(audioPlayList: AudioPlayList)
}