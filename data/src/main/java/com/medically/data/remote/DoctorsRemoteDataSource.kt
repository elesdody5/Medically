package com.medically.data.remote

import com.medically.model.Doctor
import com.medically.model.Result
import com.medically.model.Subject

interface DoctorsRemoteDataSource {
    suspend fun getDoctors(subject: Subject): Result<List<Doctor>>
}