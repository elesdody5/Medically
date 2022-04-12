package com.medically.data.integration

import com.medically.data.remote.YearsRemoteDataSource


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
    infix fun yearsRemoteDataSource(remoteDataSource: YearsRemoteDataSource) {
        RemoteDataSources.yearsRemoteDataSource = remoteDataSource
    }

}
