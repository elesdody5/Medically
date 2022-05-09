package com.medically

import android.app.Application
import android.app.NotificationManager
import android.content.ComponentName
import androidx.work.Configuration
import com.medically.core.integration.coreIntegration
import com.medically.data.integration.dataIntegrator
import com.medically.data.repository.*
import com.medically.downloader.DownloaderManager
import com.medically.downloader.worker.FileDownloadWorkerFactory
import com.medically.local.datasource.ChaptersLocalDataSourceImp
import com.medically.local.datasource.LecturesLocalDataSourceImp
import com.medically.local.db.MedicallyDatabase
import com.medically.media.connection.MusicServiceConnection
import com.medically.media.service.MediaPlaybackService
import com.medically.remote.data_source.*

class MedicallyApplication : Application(), Configuration.Provider {
    private val musicConnection by lazy {
        MusicServiceConnection.getInstance(
            this,
            ComponentName(applicationContext, MediaPlaybackService::class.java)
        )
    }
    private val notificationManager: NotificationManager by lazy {
        applicationContext.getSystemService(NotificationManager::class.java)
    }

    private val db: MedicallyDatabase by lazy {
        MedicallyDatabase.getInstance(applicationContext)
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setMinimumLoggingLevel(android.util.Log.DEBUG)
        .setWorkerFactory(FileDownloadWorkerFactory(notificationManager))
        .build()


    override fun onCreate() {
        super.onCreate()
        dataIntegrator {
            with subjectsRemoteDataSource SubjectsRemoteDataSourceImp()
            with doctorsRemoteDataSource DoctorsRemoteDataSourceImp()
            with subjectDetailsRemoteDataSource SubjectDetailsRemoteDataSourceImp()
            with chaptersRemoteDataSource ChaptersRemoteDataSourceImp()
            with chaptersLocalDataSource ChaptersLocalDataSourceImp(db.offlineDao())
            with lecturesRemoteDataSource LecturesRemoteDataSourceImp()
            with lecturesLocalDataSource LecturesLocalDataSourceImp(db.offlineDao())
        }

        coreIntegration {
            with subjectsRepository SubjectsRepository()
            with doctorsRepository DoctorsRepository()
            with subjectDetailsRepository SubjectDetailsRepository()
            with lecturesRepository LecturesRepository()
            with chapterRepository ChaptersRepository()
            with musicConnection musicConnection
            with downLoaderManager DownloaderManager(applicationContext)
        }

    }
}