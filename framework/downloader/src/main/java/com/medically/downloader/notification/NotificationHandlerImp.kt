package com.medically.downloader.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import com.medically.downloader.R

const val NOTIFICATION_ID = 1

class NotificationHandlerImp(private val notificationManager: NotificationManager) :
    NotificationHandler {
    private lateinit var builder: NotificationCompat.Builder

    override fun createNotification(
        applicationContext: Context,
        title: String,
        subTitle: String
    ) {
        createChannel(applicationContext)

        val packageName = applicationContext.packageName
        val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            applicationContext.packageManager.getLaunchIntentForPackage(packageName),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Build the notification
        builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.notification_channel_id)
        )
            .setContentTitle(title)
            .setSubText(applicationContext.getString(R.string.download_notification_title))
            .setContentText(subTitle)
            .setOngoing(true)
            .setProgress(0, 0, true)
            .setSmallIcon(R.drawable.ic_download)
            .setContentIntent(contentPendingIntent)
            .setCategory(NotificationCompat.CATEGORY_PROGRESS)

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    override fun downLoadComplete() {
        builder
            .setSubText("Download Completed")
            .setOngoing(false)
            .setSmallIcon(R.drawable.ic_app_icon)
            .setProgress(0, 0, false)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    override fun cancelDownLoadNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    override fun downLoadFailed() {
        builder.setContentText("Download Failed to complete")
            .setOngoing(false)
            .setSmallIcon(R.drawable.ic_app_icon)
            .setProgress(0, 0, false)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun createChannel(applicationContext: Context) {
        val channel = NotificationChannel(
            applicationContext.getString(R.string.notification_channel_id),
            applicationContext.getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_LOW,
        ).apply { description = applicationContext.getString(R.string.notificaiton_description) }

        notificationManager.createNotificationChannel(channel)
    }
}