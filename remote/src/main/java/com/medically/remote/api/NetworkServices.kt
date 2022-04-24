package com.medically.remote.api

import com.medically.model.*
import com.medically.remote.entities.ApiResponse


interface NetworkServices {
    suspend fun getAllSubjects(): ApiResponse<List<Subject>>
    suspend fun getDoctors(year: String, subjectId: String): ApiResponse<List<Doctor>>
    suspend fun getChapters(doctorName: String): ApiResponse<List<Chapter>>
    suspend fun getVideos(doctorName: String): ApiResponse<List<Video>>
    suspend fun getPdfs(doctorName: String): ApiResponse<List<Pdf>>
    suspend fun getLectures(chapter: String): ApiResponse<List<Lecture>>
}