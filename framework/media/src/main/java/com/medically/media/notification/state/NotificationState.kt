package com.medically.media.notification.state

import android.app.Notification

sealed class NotificationState {
    data class NotificationPosted(
        val ongoing: Boolean,
        val notificationId: Int,
        val notification: Notification
    ) : NotificationState()

    object NotificationCancelled : NotificationState()
}
