package com.medically.data.remote

import com.medically.model.Result
import com.medically.model.Year

interface YearsRemoteDataSource {
    suspend fun getAllYears(): Result<List<Year>?>
}