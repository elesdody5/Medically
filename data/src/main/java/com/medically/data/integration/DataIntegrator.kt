package com.medically.data.integration

import com.medically.data.preferences.PreferencesManager
import com.medically.data.remote.DoctorsRemoteDataSource
import com.medically.data.remote.LecturesRemoteDataSource
import com.medically.data.remote.SubjectDetailsRemoteDataSource
import com.medically.data.remote.SubjectsRemoteDataSource


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
    infix fun chaptersRemoteDataSource(remoteDataSource: SubjectDetailsRemoteDataSource) {
        RemoteDataSources.subjectDetailsRemoteDataSource = remoteDataSource
    }

    @DataIntegration
    infix fun lecturesRemoteDataSource(remoteDataSource: LecturesRemoteDataSource) {
        RemoteDataSources.lecturesRemoteDataSource = remoteDataSource
    }

    @DataIntegration
    infix fun preferencesManager(preferencesManager: PreferencesManager) {
        PreferencesDataSources.preferencesManager = preferencesManager
    }

}
