package com.medically.remote.data_source

import com.medically.data.remote.SubjectDetailsRemoteDataSource
import com.medically.model.Pdf
import com.medically.model.Result
import com.medically.model.Video
import com.medically.remote.api.FirebaseImp
import com.medically.remote.api.NetworkServices
import com.medically.remote.parseApiResponse

class SubjectDetailsRemoteDataSourceImp(private val networkServices: NetworkServices = FirebaseImp) :
    SubjectDetailsRemoteDataSource {


    override suspend fun getVideos(doctorName: String): Result<List<Video>> {
        val videos = networkServices.getVideos(doctorName)
        return videos.parseApiResponse()
    }

    override suspend fun getPdfs(doctorName: String): Result<List<Pdf>> {
        val pdfs = networkServices.getPdfs(doctorName)
        return pdfs.parseApiResponse()
    }
}