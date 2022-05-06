package com.medically.core.current_play

import com.medically.core.integration.Framework
import com.medically.core.subjects.SubjectsPort
import kotlinx.coroutines.launch

fun SubjectsPort.bindCurrentPlay() {
    scope.launch {
        Framework.musicServiceConnectionPort.nowPlaying.collect {
            state.value = state.value.copy(currentPlay = it)
        }
    }
}