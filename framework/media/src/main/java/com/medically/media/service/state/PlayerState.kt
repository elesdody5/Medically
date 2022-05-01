package com.medically.media.service.state

sealed class PlayerState {
    data class IsReady(val playWhenReady: Boolean) : PlayerState()
    object NotReady : PlayerState()
    data class Error(val message: String?) : PlayerState()
}
