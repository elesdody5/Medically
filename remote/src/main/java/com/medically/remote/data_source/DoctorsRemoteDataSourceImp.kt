package com.medically.remote.data_source

import com.medically.data.remote.DoctorsRemoteDataSource
import com.medically.model.Doctor
import com.medically.model.Result
import com.medically.model.Subject
import com.medically.remote.api.FirebaseImp
import com.medically.remote.api.NetworkServices
import com.medically.remote.parseApiResponse

class DoctorsRemoteDataSourceImp(private val networkServices: NetworkServices = FirebaseImp) :
    DoctorsRemoteDataSource {
    override suspend fun getDoctors(subject: Subject): Result<List<Doctor>> {
        val response = networkServices.getDoctors(subject.yearName, subject.id)
        return response.parseApiResponse()
    }
}