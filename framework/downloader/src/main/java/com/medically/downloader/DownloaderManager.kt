package com.medically.downloader

import android.content.Context
import androidx.work.*
import com.medically.core.downloader.DownLoaderPort
import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.downloader.entities.ChapterParams
import com.medically.downloader.worker.FileDownLoadWorker
import com.medically.model.Chapter
import com.medically.model.Lecture

class DownloaderManager(
    private val applicationContext: Context,
    private val lecturesRepositoryPort: LecturesRepositoryPort = com.medically.core.integration.Data.lecturesRepository
) : DownLoaderPort {


    override fun downLoad(lectures: List<Lecture>, chapter: Chapter) {
        lecturesRepositoryPort.setCurrentDownLoadedLectures(lectures)

        val data = Data.Builder()
        data.apply {
            putString(ChapterParams.KEY_Chapter_NAME, chapter.name)
            putString(ChapterParams.KEY_Doctor, chapter.doctorName)
            putString(ChapterParams.KEY_Chapter_IMAGE, chapter.imageUrl)
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()

        val fileDownloadWorker = OneTimeWorkRequestBuilder<FileDownLoadWorker>()
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniqueWork(
            "${FileDownLoadWorker.WORK_NAME}${System.currentTimeMillis()}",
            ExistingWorkPolicy.KEEP,
            fileDownloadWorker
        )
    }
}