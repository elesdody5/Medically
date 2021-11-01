package com.medically.data.remote

import com.medically.data.Result
import com.medically.data.entity.Subject

interface RemoteDataSource {
    suspend fun getSubjectsByYear(year: String): Result<List<Subject>>
}