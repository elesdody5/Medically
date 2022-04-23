package com.medically.data.remote

import com.medically.model.Chapter
import com.medically.model.Result

interface ChaptersRemoteDataSource {
    suspend fun getChapters(doctorName: String): Result<List<Chapter>>
}