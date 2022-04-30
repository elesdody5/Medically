package com.medically.presentation.utils

import android.content.Context
import com.medically.presentation.R
import kotlin.math.floor

const val AUDIO_PLAYLIST = "audio_playlist"

/**
 * Utility method to convert milliseconds to a display of minutes and seconds
 */
fun Long.timestampToMSS(context: Context): String {
    val totalSeconds = floor(this / 1E3).toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds - (minutes * 60)
    return if (this < 0) context.getString(R.string.duration_unknown)
    else context.getString(R.string.duration_format).format(minutes, remainingSeconds)
}

