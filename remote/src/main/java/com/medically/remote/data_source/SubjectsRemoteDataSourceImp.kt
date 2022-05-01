package com.medically.remote.data_source

import com.medically.data.remote.SubjectsRemoteDataSource
import com.medically.model.Result
import com.medically.model.Subject
import com.medically.remote.api.FirebaseImp
import com.medically.remote.api.NetworkServices
import com.medically.remote.parseApiResponse

class SubjectsRemoteDataSourceImp(private val networkServices: NetworkServices = FirebaseImp) :
    SubjectsRemoteDataSource {
    override suspend fun getAllSubjects(): Result<List<Subject>?> {
        val response = networkServices.getAllSubjects()
        return response.parseApiResponse()
    }
}