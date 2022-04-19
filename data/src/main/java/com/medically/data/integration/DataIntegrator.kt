package com.medically.data.integration

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

}
