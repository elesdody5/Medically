package com.medically.data.repository

import com.medically.data.Result
import com.medically.domain.model.Subject

interface Repository {
    suspend fun getSubjectsByYear(year: String): Result<List<Subject>>
}