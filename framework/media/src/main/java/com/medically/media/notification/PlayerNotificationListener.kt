package com.medically.media.notification

import android.app.Notification
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.medically.media.notification.state.NotificationState
import com.medically.media.notification.state.NotificationState.NotificationCancelled
import com.medically.media.notification.state.NotificationState.NotificationPosted
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Listen for notification events.
 */
class PlayerNotificationListener : PlayerNotificationManager.NotificationListener {
    private val _notificationState = MutableStateFlow<NotificationState?>(null)
    val notificationState: Flow<NotificationState?>
        get() = _notificationState

    override fun onNotificationPosted(
        notificationId: Int,
        notification: Notification,
        ongoing: Boolean
    ) {
        _notificationState.value = NotificationPosted(ongoing, notificationId, notification)
    }

    override fun onNotificationCancelled(
        notificationId: Int,
        dismissedByUser: Boolean
    ) {
        _notificationState.value = NotificationCancelled
    }
}