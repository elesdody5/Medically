package com.medically.data.integration

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.years.YearsRepositoryPort
import com.medically.data.remote.YearsRemoteDataSource

object RemoteDataSources {
    lateinit var yearsRemoteDataSource: YearsRemoteDataSource

}

object LocalDataSources {

}