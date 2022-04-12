package com.medically.data.repository

import com.medically.core.years.YearsRepositoryPort
import com.medically.data.integration.RemoteDataSources
import com.medically.data.remote.YearsRemoteDataSource
import com.medically.model.Result
import com.medically.model.Year

class YearsRepositoryImp(
    private val yearsRemoteDataSource: YearsRemoteDataSource = RemoteDataSources.yearsRemoteDataSource
) : YearsRepositoryPort {
    override suspend fun getAllYears(): Result<List<Year>?> {
        return yearsRemoteDataSource.getAllYears()
    }
}