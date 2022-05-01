package com.medically.remote.data_source

import com.medically.data.remote.LecturesRemoteDataSource
import com.medically.model.Lecture
import com.medically.model.Result
import com.medically.remote.api.FirebaseImp
import com.medically.remote.api.NetworkServices
import com.medically.remote.parseApiResponse

class LecturesRemoteDataSourceImp(val networkServices: NetworkServices = FirebaseImp) :
    LecturesRemoteDataSource {
    override suspend fun getChapterLectures(chapter: String): Result<List<Lecture>> {
        val response = networkServices.getLectures(chapter)
        return response.parseApiResponse()
    }
}