package com.medically.downloader.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.downloader.data_handler.DownLoadDataHandler
import com.medically.downloader.getChapter
import com.medically.downloader.getLecture
import com.medically.downloader.notification.NotificationHandler
import com.medically.model.Lecture

class FileDownLoadWorker constructor(
    appContext: Context,
    workerParams: WorkerParameters,
    private val notificationHandler: NotificationHandler,
    private val dataHandler: DownLoadDataHandler,
    private val lecturesRepositoryPort: LecturesRepositoryPort
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        const val WORK_NAME = "FileDownLoadWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        return try {
            val lecture = inputData.getLecture()
            val chapter = inputData.getChapter()
            if (lecture.name.isEmpty() || lecture.url.isEmpty()) {
                Result.failure()
            }
            notificationHandler.createNotification(
                applicationContext,
                lecture.name,
                chapter.name
            )
            val uri = dataHandler.downLoadFile(
                applicationContext,
                chapter.name,
                lecture.name,
                lecture.url
            )

            return if (uri != null) {
                notificationHandler.downLoadComplete()
                Log.i("worker", "DownLoad success")
                lecturesRepositoryPort.insertOfflineLectures(
                    chapter = chapter,
                    Lecture(lecture.name, uri.toString(), chapterName = chapter.name)
                )
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }


}