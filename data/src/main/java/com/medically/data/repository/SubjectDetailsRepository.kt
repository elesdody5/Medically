package com.medically.data.repository

import com.medically.core.subject_details.SubjectDetailsRepositoryPort
import com.medically.data.integration.RemoteDataSources
import com.medically.data.remote.SubjectDetailsRemoteDataSource
import com.medically.model.Pdf
import com.medically.model.Result
import com.medically.model.Video

class SubjectDetailsRepository(
    private val subjectsDetailsRemoteDataSource: SubjectDetailsRemoteDataSource = RemoteDataSources.subjectDetailsRemoteDataSource
) : SubjectDetailsRepositoryPort {


    override suspend fun getVideos(doctorName: String): Result<List<Video>> {
        return subjectsDetailsRemoteDataSource.getVideos(doctorName)
    }

    override suspend fun getPdfs(doctorName: String): Result<List<Pdf>> {
        return subjectsDetailsRemoteDataSource.getPdfs(doctorName)
    }
}