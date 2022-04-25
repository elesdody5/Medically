package com.medically.core.player

import com.medically.core.persentation.PresentationPort
import kotlinx.coroutines.flow.MutableStateFlow

interface PlayerPort : PresentationPort<PlayerPortState> {
    val mediaPosition: MutableStateFlow<Long>
    var updatePosition: Boolean
}