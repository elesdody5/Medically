package com.medically

import android.app.Application
import android.content.ComponentName
import com.medically.core.integration.coreIntegration
import com.medically.data.integration.dataIntegrator
import com.medically.data.repository.DoctorsRepository
import com.medically.data.repository.LecturesRepository
import com.medically.data.repository.SubjectDetailsRepository
import com.medically.data.repository.SubjectsRepository
import com.medically.media.service.MediaPlaybackService
import com.medically.media.service.MusicServiceConnection
import com.medically.preferences.PreferencesImp
import com.medically.remote.data_source.DoctorsRemoteDataSourceImp
import com.medically.remote.data_source.LecturesRemoteDataSourceImp
import com.medically.remote.data_source.SubjectDetailsRemoteDataSourceImp
import com.medically.remote.data_source.SubjectsRemoteDataSourceImp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedicallyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val musicConnection = MusicServiceConnection.getInstance(
            applicationContext,
            ComponentName(applicationContext, MediaPlaybackService::class.java)
        )
        dataIntegrator {
            with subjectsRemoteDataSource SubjectsRemoteDataSourceImp()
            with doctorsRemoteDataSource DoctorsRemoteDataSourceImp()
            with chaptersRemoteDataSource SubjectDetailsRemoteDataSourceImp()
            with lecturesRemoteDataSource LecturesRemoteDataSourceImp()
            with preferencesManager PreferencesImp(this@MedicallyApplication)
        }

        coreIntegration {
            with subjectsRepository SubjectsRepository()
            with doctorsRepository DoctorsRepository()
            with subjectDetailsRepository SubjectDetailsRepository()
            with lecturesRepository LecturesRepository()
            with musicConnection musicConnection
        }

    }
}