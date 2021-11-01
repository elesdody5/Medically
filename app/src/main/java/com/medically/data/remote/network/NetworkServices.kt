package com.medically.data.remote.network

import com.medically.data.entity.ApiResponse
import com.medically.data.entity.Subject

interface NetworkServices {
    suspend fun getSubjectsByYear(year: String): ApiResponse<List<Subject>>
}