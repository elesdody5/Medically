package com.medically.data.integration

import com.medically.data.remote.ChaptersRemoteDataSource
import com.medically.data.remote.DoctorsRemoteDataSource
import com.medically.data.remote.SubjectsRemoteDataSource
import com.medically.model.Chapter


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
    infix fun chaptersRemoteDataSource(remoteDataSource: ChaptersRemoteDataSource) {
        RemoteDataSources.chaptersRemoteDataSource = remoteDataSource
    }

}
