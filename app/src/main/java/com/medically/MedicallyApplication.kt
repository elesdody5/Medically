package com.medically

import android.app.Application
import android.app.NotificationManager
import android.content.ComponentName
import androidx.work.Configuration
import com.medically.core.integration.coreIntegration
import com.medically.data.integration.dataIntegrator
import com.medically.data.repository.DoctorsRepository
import com.medically.data.repository.LecturesRepository
import com.medically.data.repository.SubjectDetailsRepository
import com.medically.data.repository.SubjectsRepository
import com.medically.downloader.DownloaderManager
import com.medically.downloader.worker.FileDownloadWorkerFactory
import com.medically.local.datasource.LecturesLocalDataSourceImp
import com.medically.local.db.MedicallyDatabase
import com.medically.media.connection.MusicServiceConnection
import com.medically.media.service.MediaPlaybackService
import com.medically.remote.data_source.DoctorsRemoteDataSourceImp
import com.medically.remote.data_source.LecturesRemoteDataSourceImp
import com.medically.remote.data_source.SubjectDetailsRemoteDataSourceImp
import com.medically.remote.data_source.SubjectsRemoteDataSourceImp

class MedicallyApplication : Application(), Configuration.Provider {

    private val notificationManager: NotificationManager by lazy {
        applicationContext.getSystemService(NotificationManager::class.java)
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setMinimumLoggingLevel(android.util.Log.DEBUG)
        .setWorkerFactory(FileDownloadWorkerFactory(notificationManager))
        .build()


    override fun onCreate() {
        super.onCreate()
        val musicConnection = MusicServiceConnection.getInstance(
            this,
            ComponentName(applicationContext, MediaPlaybackService::class.java)
        )

        val db = MedicallyDatabase.getInstance(applicationContext)
        dataIntegrator {
            with subjectsRemoteDataSource SubjectsRemoteDataSourceImp()
            with doctorsRemoteDataSource DoctorsRemoteDataSourceImp()
            with chaptersRemoteDataSource SubjectDetailsRemoteDataSourceImp()
            with lecturesRemoteDataSource LecturesRemoteDataSourceImp()
            with lecturesLocalDataSource LecturesLocalDataSourceImp(db.lecturesDao())
        }

        coreIntegration {
            with subjectsRepository SubjectsRepository()
            with doctorsRepository DoctorsRepository()
            with subjectDetailsRepository SubjectDetailsRepository()
            with lecturesRepository LecturesRepository()
            with musicConnection musicConnection
            with downLoaderManager DownloaderManager(applicationContext)
        }

    }
}