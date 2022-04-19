package com.medically.data.remote

import com.medically.model.Result
import com.medically.model.Subject

interface SubjectsRemoteDataSource {
    suspend fun getAllSubjects(): Result<List<Subject>?>
}