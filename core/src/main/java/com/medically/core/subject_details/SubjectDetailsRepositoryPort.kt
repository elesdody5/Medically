package com.medically.core.subject_details

import com.medically.model.Pdf
import com.medically.model.Result
import com.medically.model.Video

interface SubjectDetailsRepositoryPort {
    suspend fun getVideos(doctorName: String): Result<List<Video>>
    suspend fun getPdfs(doctorName: String): Result<List<Pdf>>
}
