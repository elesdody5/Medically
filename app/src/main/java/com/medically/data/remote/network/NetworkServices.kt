package com.medically.data.remote.network

import com.medically.data.entity.ApiResponse
import com.medically.domain.model.*

interface NetworkServices {
    suspend fun getSubjectsByYear(year: String): ApiResponse<List<Subject>>
    suspend fun getDoctorChapters(subject: String, doctor: String): ApiResponse<List<Chapter>>
    suspend fun getDoctorPdfs(doctor: String): ApiResponse<List<Pdf>>
    suspend fun getDoctorVideo(doctor: String): ApiResponse<Video>
    suspend fun getChapterLectures(chapter: String): ApiResponse<List<Lecture>>
}