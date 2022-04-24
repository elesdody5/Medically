package com.medically.data.repository

import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.data.InMemoryCache
import com.medically.data.integration.RemoteDataSources
import com.medically.data.remote.DoctorsRemoteDataSource
import com.medically.model.Doctor
import com.medically.model.Result

class DoctorsRepositoryImp(
    private val doctorsRemoteDataSource: DoctorsRemoteDataSource = RemoteDataSources.doctorsRemoteDataSource,
) : DoctorsRepositoryPort {
    override suspend fun getDoctors(): Result<List<Doctor>> {
        val subject = InMemoryCache.currentSubject
        subject?.let {
            return doctorsRemoteDataSource.getDoctors(it)
        }
        return Result.Error(Exception("Subject not found"))

    }
}