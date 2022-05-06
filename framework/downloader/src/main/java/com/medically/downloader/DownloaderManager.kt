package com.medically.downloader

import android.content.Context
import androidx.work.*
import com.medically.core.downloader.DownLoaderPort
import com.medically.downloader.entities.ChapterParams
import com.medically.downloader.entities.LectureParams.KEY_LECTURE_NAME
import com.medically.downloader.entities.LectureParams.KEY_LECTURE_URL
import com.medically.downloader.worker.FileDownLoadWorker
import com.medically.model.Chapter
import com.medically.model.Lecture

class DownloaderManager(private val applicationContext: Context) : DownLoaderPort {


    override fun downLoad(lecture: Lecture, chapter: Chapter) {
        val data = Data.Builder()

        data.apply {
            putString(KEY_LECTURE_NAME, lecture.name)
            putString(KEY_LECTURE_URL, lecture.url)
            putString(ChapterParams.KEY_Chapter_id, chapter.id)
            putString(ChapterParams.KEY_Chapter_NAME, chapter.name)
            putString(ChapterParams.KEY_Doctor, chapter.doctorName)
            putString(ChapterParams.KEY_Chapter_IMAGE, chapter.imageUrl)
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
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