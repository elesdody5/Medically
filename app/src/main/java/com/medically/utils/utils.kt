package com.medically.utils

import android.content.Context
import com.medically.R

/**
 * Utility method to convert milliseconds to a display of minutes and seconds
 */
fun timestampToMSS(context: Context, position: Long): String {
    val totalSeconds = Math.floor(position / 1E3).toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds - (minutes * 60)
    return if (position < 0) context.getString(R.string.duration_unknown)
    else context.getString(R.string.duration_format).format(minutes, remainingSeconds)
}