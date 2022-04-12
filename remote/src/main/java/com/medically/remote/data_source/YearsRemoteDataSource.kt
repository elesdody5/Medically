package com.medically.remote.data_source

import com.medically.data.remote.YearsRemoteDataSource
import com.medically.model.Result
import com.medically.model.Year
import com.medically.remote.api.FirebaseImp
import com.medically.remote.api.NetworkServices
import java.lang.Exception

class YearsRemoteDataSource(private val networkServices: NetworkServices = FirebaseImp()) :
    YearsRemoteDataSource {
    override suspend fun getAllYears(): Result<List<Year>?> {
        val response = networkServices.getAllYears()
        return if (response.exception == null) {
            Result.Success(response.data)
        } else {
            return Result.Error(response.exception ?: Exception())
        }
    }
}