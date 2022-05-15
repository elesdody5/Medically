package com.medically.downloader.worker

import android.app.NotificationManager
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.medically.core.integration.Data
import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.downloader.data_handler.DownLoadDataHandler
import com.medically.downloader.data_handler.DownLoadDataHandlerImp
import com.medically.downloader.notification.NotificationHandler
import com.medically.downloader.notification.NotificationHandlerImp

class FileDownloadWorkerFactory(
    private val notificationManager: NotificationManager,
    private val notificationHandler: NotificationHandler = NotificationHandlerImp(
        notificationManager
    ),
    private val downloadHandler: DownLoadDataHandler = DownLoadDataHandlerImp(),
    private val lecturesRepositoryPort: LecturesRepositoryPort = Data.lecturesRepository
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {

        return FileDownLoadWorker(
            appContext,
            workerParameters,
            notificationHandler,
            downloadHandler,
            lecturesRepositoryPort
        )
    }
}