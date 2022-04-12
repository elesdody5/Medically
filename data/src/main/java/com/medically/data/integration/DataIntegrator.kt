package com.medically.data.integration

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.integration.Tracking
import com.medically.core.years.YearsRepositoryPort
import com.medically.core.tracking.Logger
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
