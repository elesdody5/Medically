package com.medically.remote.api

import com.medically.model.*


interface NetworkServices {
    suspend fun getAllSubjects(): ApiResponse<List<Subject>>
    suspend fun getDoctors(year: String, subjectId: String): ApiResponse<List<Doctor>>
    suspend fun getChapters(doctorName: String): ApiResponse<List<Chapter>>
}