package com.medically.data.remote

import com.medically.data.Result
import com.medically.data.entity.ApiResponse
import com.medically.data.entity.Subject
import com.medically.data.remote.network.NetworkServices
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val networkServices: NetworkServices) :
    RemoteDataSource {
    override suspend fun getSubjectsByYear(year: String): Result<List<Subject>> {
        val response = networkServices.getSubjectsByYear(year)
        return getDataFromResponse(response)
    }

    private fun <T> getDataFromResponse(response: ApiResponse<T>): Result<T> {
        return if (response.data != null) {
            return Result.Success(response.data)
        } else {
            Result.Error(response.exception!!)
        }
    }
}