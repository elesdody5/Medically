package com.medically.remote.data_source

import com.medically.data.remote.ChaptersRemoteDataSource
import com.medically.model.Chapter
import com.medically.model.Result
import com.medically.remote.api.FirebaseImp
import com.medically.remote.api.NetworkServices
import com.medically.remote.parseApiResponse

class ChaptersRemoteDataSourceImp(private val networkServices: NetworkServices = FirebaseImp) :
    ChaptersRemoteDataSource {
    override suspend fun getChapters(doctorName: String): Result<List<Chapter>> {
        val chapters = networkServices.getChapters(doctorName)
        return chapters.parseApiResponse()
    }
}