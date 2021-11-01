package com.medically.data.repository

import com.medically.data.Result
import com.medically.data.remote.RemoteDataSource
import com.medically.domain.model.Subject
import javax.inject.Inject

class MainRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun getSubjectsByYear(year: String): Result<List<Subject>> {
        return remoteDataSource.getSubjectsByYear(year)
    }
}