package com.medically.downloader.notification

import android.content.Context

interface NotificationHandler {
    fun createNotification(
        applicationContext: Context,
        title: String,
        subTitle: String
    )

    fun downLoadComplete()
    fun cancelDownLoadNotification()
}