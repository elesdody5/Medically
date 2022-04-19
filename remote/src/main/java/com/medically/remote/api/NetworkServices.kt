package com.medically.remote.api

import com.medically.model.*


interface NetworkServices {
    suspend fun getAllYears(): ApiResponse<List<Year>>
    suspend fun getAllSubjects(): ApiResponse<List<Subject>>
}