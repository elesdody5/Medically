package com.medically.data.integration

import com.medically.data.local.ChaptersLocalDataSource
import com.medically.data.local.LecturesLocalDataSource
import com.medically.data.preferences.PreferencesManager
import com.medically.data.remote.*


@DslMarker
private annotation class DataIntegration

@DataIntegration
fun dataIntegrator(integrator: DataIntegrator.() -> Unit) {
    DataIntegrator.apply(integrator)
}

object DataIntegrator {
    @DataIntegration
    val with = this


    @DataIntegration
    infix fun subjectsRemoteDataSource(remoteDataSource: SubjectsRemoteDataSource) {
        RemoteDataSources.subjectsDataSource = remoteDataSource
    }

    @DataIntegration
    infix fun doctorsRemoteDataSource(remoteDataSource: DoctorsRemoteDataSource) {
        RemoteDataSources.doctorsRemoteDataSource = remoteDataSource
    }

    @DataIntegration
    infix fun subjectDetailsRemoteDataSource(remoteDataSource: SubjectDetailsRemoteDataSource) {
        RemoteDataSources.subjectDetailsRemoteDataSource = remoteDataSource
    }

    @DataIntegration
    infix fun chaptersRemoteDataSource(remoteDataSource: ChaptersRemoteDataSource) {
        RemoteDataSources.chaptersRemoteDataSource = remoteDataSource
    }

    @DataIntegration
    infix fun chaptersLocalDataSource(chaptersLocalDataSource: ChaptersLocalDataSource) {
        LocalDataSources.chaptersLocalDatasource = chaptersLocalDataSource
    }

    @DataIntegration
    infix fun lecturesRemoteDataSource(remoteDataSource: LecturesRemoteDataSource) {
        RemoteDataSources.lecturesRemoteDataSource = remoteDataSource
    }

    @DataIntegration
    infix fun lecturesLocalDataSource(lecturesLocalDataSource: LecturesLocalDataSource) {
        LocalDataSources.lecturesLocalDataSource = lecturesLocalDataSource
    }

    @DataIntegration
    infix fun preferencesManager(preferencesManager: PreferencesManager) {
        PreferencesDataSources.preferencesManager = preferencesManager
    }

}
